package com.allen.service.fee.studentfee.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.fee.FeeType;
import com.allen.entity.fee.StudentFee;
import com.allen.service.fee.studentfee.AddStudentFeeService;
import com.allen.service.fee.studentfee.ImportStudentFeeService;
import com.allen.util.*;
import com.allen.util.excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/12.
 */
@Service
public class ImportStudentFeeServiceImpl implements ImportStudentFeeService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AddStudentFeeService addStudentFeeService;
    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private TeachPlanDao teachPlanDao;


    @Override
    @Transactional
    public JSONObject importStudentFee(HttpServletRequest request, long schoolId) throws Exception {
        JSONObject returnJSON = new JSONObject();
        String msg = "";
        String studentIds = "";
        List<Map<String, String>> studentFeeMapList = new ArrayList<Map<String, String>>();
        long centerId = UserUtil.getLoginUserForCenterId(request);
        MultipartRequest mulReu = (MultipartRequest)request;
        if(null == mulReu){
            throw new BusinessException("没有上传文件");
        }
        List<MultipartFile> fileList = mulReu.getFiles("file");
        if(null == fileList){
            throw new BusinessException("没有解析到上传文件");
        }
        for(MultipartFile multipartFile : fileList){
            String fileName = multipartFile.getOriginalFilename();
            if (!ExcelUtil.validateExcel(fileName)) {
                throw new BusinessException("只能上传excel文件");
            }
            boolean is2007 = ExcelUtil.isExcel2007(fileName);
            InputStream inputStream = multipartFile.getInputStream();

            msg = readXls(inputStream, is2007, studentFeeMapList, msg, schoolId, centerId);
            returnJSON.put("msg", msg);
            if(StringUtil.isEmpty(msg)){
                for (Map<String, String> map : studentFeeMapList) {
                    StudentFee studentFee = new StudentFee();
                    String studentId = map.get("studentId");
                    if(studentIds.indexOf(studentId) < 0){
                        studentIds += studentId+",";
                    }
                    studentFee.setStudentId(Long.parseLong(map.get("studentId")));
                    studentFee.setFeeTypeId(Long.parseLong(map.get("feeTypeId")));
                    studentFee.setFeeStyle(Integer.parseInt(map.get("feeStyle")));
                    studentFee.setFee(Long.parseLong(new BigDecimal(map.get("fee")).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString()));
                    if(!StringUtil.isEmpty(map.get("fxsFee"))) {
                        studentFee.setFxsFee(Long.parseLong(new BigDecimal(map.get("fxsFee")).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString()));
                    }
                    studentFee.setOperator(UserUtil.getLoginUserForName(request));
                    addStudentFeeService.add(studentFee, 0);
                    returnJSON.put("studentIds", StringUtil.isEmpty(studentIds)  ? "" : studentIds.substring(0, studentIds.length()-1));
                }
                returnJSON.put("state", 0);
            }else{
                returnJSON.put("state", 1);
            }
        }
        return returnJSON;
    }

    protected String readXls(InputStream inputStream, boolean is2007, List<Map<String, String>> studentFeeMapList, String msg, long schoolId, long centerId)throws Exception{
        Workbook wb = null;
        if(is2007){
            wb = new XSSFWorkbook(inputStream);
        }else{
            wb = new HSSFWorkbook(inputStream);
        }
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            if(sheet.getLastRowNum() < 1){
                msg += "上传文件里面没有数据";
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                String code = row.getCell((short)0).toString().trim();
                String feeTypeName = row.getCell((short)1).toString().trim();
                String feeStyleName = row.getCell((short)2).toString().trim();
                String fee = row.getCell((short)3).toString().trim();
                String fxsFee = "";

                if(StringUtil.isEmpty(code)){
                    msg += "第"+rowNum+"行：学号为空<br />";
                }
                if(StringUtil.isEmpty(feeTypeName)){
                    msg += "第"+rowNum+"行：缴费类型为空<br />";
                }
                if(StringUtil.isEmpty(feeStyleName)){
                    msg += "第"+rowNum+"行：缴费方式为空<br />";
                }
                if(!StringUtil.isEmpty(feeStyleName) && !"平台".equals(feeStyleName) && !"分销商".equals(feeStyleName) && !"中心".equals(feeStyleName)){
                    msg += "第"+rowNum+"行：缴费方式错误，只能是平台、分销商、中心<br />";
                }
                if(StringUtil.isEmpty(fee)){
                    msg += "第"+rowNum+"行：缴费金额为空<br />";
                }
                if(!ZzUtil.isMoney(fee)){
                    msg += "第"+rowNum+"行：缴费金额格式错误<br />";
                }
                if("分销商".equals(feeStyleName)) {
                    if(null == row.getCell((short)4)){
                        msg += "第" + rowNum + "行：分销商实缴为空<br />";
                    }else {
                        fxsFee = row.getCell((short) 4).toString();
                        if (StringUtil.isEmpty(fxsFee)) {
                            msg += "第" + rowNum + "行：分销商实缴为空<br />";
                        }
                        if (!ZzUtil.isMoney(fxsFee)) {
                            msg += "第" + rowNum + "行：分销商实缴金额格式错误<br />";
                        }
                    }
                }

                code = code.replace(".0", "");
                Student student = studentDao.findBySchoolIdAndCode(schoolId, code);
                FeeType feeType = null;
                if(null == student){
                    msg += "第"+rowNum+"行：学号"+code+"再高校里面没有找到<br />";
                }else {
                    TeachPlan teachPlan = teachPlanDao.findOne(student.getTeachPlanId());
                    if (null == teachPlan) {
                        msg += "第" + rowNum + "行：学号" + code + "再没有找到教学计划<br />";
                    }else {
                        feeType = feeTypeDao.findByNameAndSchoolIdAndTypeIdAndLevelIdAndYearAndTerm(feeTypeName, schoolId, student.getRecruitTypeId(), student.getLevelId(), teachPlan.getYear(), teachPlan.getTerm());
                        if (null == feeType) {
                            msg += "第" + rowNum + "行：学号" + code + "不存在缴费类型：" + feeTypeName + "<br />";
                        }
                    }
                }

                Map<String, String> map = new HashMap<String, String>(5);
                map.put("studentId", null == student ? "0" : student.getId()+"");
                map.put("feeTypeId", null == feeType ? "0" : feeType.getId()+"");
                map.put("feeStyle", "平台".equals(feeStyleName) ? "0" : "分销商".equals(feeStyleName) ? "1" : "2");
                map.put("fee", fee);
                map.put("fxsFee", fxsFee);
                studentFeeMapList.add(map);
            }
        }
        return msg;
    }
}
