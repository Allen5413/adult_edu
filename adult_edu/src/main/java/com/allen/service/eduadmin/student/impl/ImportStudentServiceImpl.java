package com.allen.service.eduadmin.student.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.Spec;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.recruit.SignUp;
import com.allen.service.eduadmin.student.AddStudentService;
import com.allen.service.eduadmin.student.ImportStudentService;
import com.allen.util.ExcelUtil;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import com.allen.util.UserUtil;
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
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Allen on 2017/7/12.
 */
@Service
public class ImportStudentServiceImpl implements ImportStudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private LevelDao levelDao;
    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Autowired
    private TeachPlanDao teachPlanDao;
    @Autowired
    private SpecDao specDao;
    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private AddStudentService addStudentService;
    @Autowired
    private ConfigProp configProp;


    @Override
    @Transactional
    public JSONObject importStudent(HttpServletRequest request, long schoolId) throws Exception {
        JSONObject returnJSON = new JSONObject();
        String msg = "";
        Map<String, SignUp> codeMap = new HashMap<String, SignUp>();
        String str = "";
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

            msg = readXls(inputStream, is2007, codeMap, str, msg, schoolId, centerId);
            returnJSON.put("msg", msg);
            if(StringUtil.isEmpty(msg)){
                for (Map.Entry<String, SignUp> entry : codeMap.entrySet()) {
                    String code = entry.getKey();
                    SignUp signUp = entry.getValue();
                    Student student = new Student();
                    student.setCenterId(signUp.getCenterId());
                    student.setUserId(signUp.getUserId());
                    student.setSchoolId(signUp.getSchoolId());
                    student.setRecruitTypeId(signUp.getRecruitTypeId());
                    student.setLevelId(signUp.getLevelId());
                    student.setSpecId(signUp.getSpecId());
                    student.setTeachPlanId(signUp.getTeachPlanId());
                    student.setState(Student.STATE_NORMAL);
                    student.setCode(code);
                    student.setName(signUp.getName());
                    student.setSex(signUp.getSex());
                    student.setIdCard(signUp.getIdCard());
                    student.setEmail(signUp.getEmail());
                    student.setBirthday(signUp.getIdCard().substring(6, 10) + "-" + signUp.getIdCard().substring(10, 12) + "-" + signUp.getIdCard().substring(12, 14));
                    student.setSignUpDate(Timestamp.valueOf(signUp.getCreateTime().toString()));
                    student.setStudyType(signUp.getStudyType());
                    student.setZipCode(signUp.getZipCode());
                    student.setPhone(signUp.getPhone());
                    student.setAddress(signUp.getAddress());
                    student.setPhotoUrl(signUp.getPhotoUrl());
                    student.setCerator(UserUtil.getLoginUserForName(request));
                    student.setOperator(UserUtil.getLoginUserForName(request));
                    addStudentService.add(student);

                    if(!StringUtil.isEmpty(signUp.getPhotoUrl())){
                        UpLoadFileUtil.copyFile(request, signUp.getPhotoUrl(), configProp.getStudent().get("photoUrl"), student.getId()+".png");
                        student.setPhotoUrl(configProp.getDomain().get("xiwang")+configProp.getStudent().get("photoUrl")+student.getId()+".png");
                    }
                    if(!StringUtil.isEmpty(signUp.getIdCardFrontUrl())){
                        UpLoadFileUtil.copyFile(request, signUp.getIdCardFrontUrl(), configProp.getStudent().get("idCardFrontUrl"), student.getId()+".png");
                        student.setIdCardFrontUrl(configProp.getDomain().get("xiwang")+configProp.getStudent().get("idCardFrontUrl") + student.getId() + ".png");
                    }
                    if(!StringUtil.isEmpty(signUp.getIdCardBackUrl())){
                        UpLoadFileUtil.copyFile(request, signUp.getIdCardBackUrl(), configProp.getStudent().get("idCardBackUrl"), student.getId()+".png");
                        student.setIdCardBackUrl(configProp.getDomain().get("xiwang")+configProp.getStudent().get("idCardBackUrl") + student.getId() + ".png");
                    }
                    if(!StringUtil.isEmpty(signUp.getDiplomaUrl())){
                        UpLoadFileUtil.copyFile(request, signUp.getDiplomaUrl(), configProp.getStudent().get("diplomaUrl"), student.getId()+".png");
                        student.setDiplomaUrl(configProp.getDomain().get("xiwang")+configProp.getStudent().get("diplomaUrl") + student.getId() + ".png");
                    }
                    if(!StringUtil.isEmpty(signUp.getXxwUrl())){
                        UpLoadFileUtil.copyFile(request, signUp.getXxwUrl(), configProp.getStudent().get("xxwUrl"), student.getId()+".png");
                        student.setXxwUrl(configProp.getDomain().get("xiwang")+configProp.getStudent().get("xxwUrl") + student.getId() + ".png");
                    }
                    if(!StringUtil.isEmpty(signUp.getYdsUrl())){
                        UpLoadFileUtil.copyFile(request, signUp.getYdsUrl(), configProp.getStudent().get("ydsUrl"), student.getId()+".png");
                        student.setYdsUrl(configProp.getDomain().get("xiwang")+configProp.getStudent().get("ydsUrl") + student.getId() + ".png");
                    }
                    studentDao.save(student);

                    signUp.setState(SignUp.STATE_SCHOOL_PASS);
                    signUpDao.save(signUp);
                }
                returnJSON.put("state", 0);
            }else{
                returnJSON.put("state", 1);
            }
        }
        return returnJSON;
    }


    protected String readXls(InputStream inputStream, boolean is2007, Map<String, SignUp> codeMap, String str, String msg, long schoolId, long centerId)throws Exception{
        List<JSONObject> list = new ArrayList<JSONObject>();
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
                JSONObject json = new JSONObject();
                Row row = sheet.getRow(rowNum);
                String teachPlanName = row.getCell((short)0).toString().trim();
                String rtName = row.getCell((short)1).toString().trim();
                String idCard = row.getCell((short)2).toString().trim();
                String levelName = row.getCell((short)3).toString().trim();
                String specCode = row.getCell((short)4).toString().trim();
                String code = row.getCell((short)5).toString().trim();

                if(StringUtil.isEmpty(teachPlanName)){
                    msg += "第"+rowNum+"行：入学批次为空<br />";
                }
                if(StringUtil.isEmpty(rtName)){
                    msg += "第"+rowNum+"行：招生类型为空<br />";
                }
                if(StringUtil.isEmpty(idCard)){
                    msg += "第"+rowNum+"行：身份证号为空<br />";
                }
                if(StringUtil.isEmpty(levelName)){
                    msg += "第"+rowNum+"行：层次为空<br />";
                }
                if(StringUtil.isEmpty(specCode)){
                    msg += "第"+rowNum+"行：专业编号为空<br />";
                }
                if(StringUtil.isEmpty(code)){
                    msg += "第"+rowNum+"行：学号为空<br />";
                }

                if(str.indexOf(teachPlanName+"_@_"+idCard+"_@_"+levelName+"_@_"+specCode+"_@_"+code) > -1){
                    msg += "第"+rowNum+"行：入学批次+身份证号+层次+专业编号 在文件里有重复的数据<br />";
                    continue;
                }
                str += teachPlanName+"_@_"+idCard+"_@_"+levelName+"_@_"+specCode+"_@_"+code;

                SignUp signUp = codeMap.get(code);
                if(null != signUp){
                    msg += "第"+rowNum+"行：学号在文件里面有重复<br />";
                    continue;
                }

                int year = Integer.parseInt(teachPlanName.substring(0,4).toString());
                if(!"春".equals(teachPlanName.substring(4)) && !"秋".equals(teachPlanName.substring(4))){
                    msg += "第"+rowNum+"行：入学批次格式错误<br />";
                    continue;
                }
                int term = "春".equals(teachPlanName.substring(4)) ? TeachPlan.TERM_SPRING : TeachPlan.TERM_AUTUMN;

                RecruitType recruitType = recruitTypeDao.findByCenterIdAndName(centerId, rtName);
                if(null == recruitType){
                    msg += "第"+rowNum+"行：招生类型不存在<br />";
                    continue;
                }

                Level level = levelDao.findByCenterIdAndName(centerId, levelName);
                if(null == level){
                    msg += "第"+rowNum+"行：层次名称不存在<br />";
                    continue;
                }

                Spec spec = specDao.findByCodeAndSchoolId(specCode, schoolId);
                if(null == spec){
                    msg += "第"+rowNum+"行：专业编号不存在<br />";
                    continue;
                }

                TeachPlan teachPlan = teachPlanDao.findBySchoolIdAndTypeIdAndLevelIdAndSpecIdAndYearAndTerm(schoolId, recruitType.getId(), level.getId(), spec.getId(), year, term);
                signUp = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(centerId, schoolId, recruitType.getId(), level.getId(), spec.getId(), null == teachPlan ? 0l : teachPlan.getId(), idCard);
                Student student = studentDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndIdCard(centerId, schoolId, recruitType.getId(), level.getId(), spec.getId(), null == teachPlan ? 0l : teachPlan.getId(), idCard);
                if(null == signUp){
                    msg += "第"+rowNum+"行：入学批次+招生类型+层次+专业编号中的身份证号在报名信息中不存在<br />";
                }else{
                    if(null != student && !StringUtil.isEmpty(student.getCode()) && !student.getCode().equals(code)){
                        msg += "第"+rowNum+"行：该学生的学号已经存在，是"+student.getCode()+"<br />";
                    }
                }
                student = studentDao.findBySchoolIdAndCode(schoolId, code);
                if(null != student){
                    msg += "第"+rowNum+"行：学号已经在系统里存在，学生姓名是"+student.getName()+"<br />";
                }

                if(!StringUtil.isEmpty(code)) {
                    codeMap.put(code, signUp);
                }
                list.add(json);
            }
        }
        return msg;
    }
}
