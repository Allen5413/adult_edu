package com.allen.service.basic.schooltypelevelspeccourse.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.course.CourseDao;
import com.allen.dao.basic.schooltypelevelspec.SchoolTypeLevelSpecDao;
import com.allen.entity.basic.Course;
import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.entity.basic.SchoolTypeLevelSpecCourse;
import com.allen.service.basic.schooltypelevelspeccourse.AddSchoolTypeLevelSpecCourseService;
import com.allen.service.basic.schooltypelevelspeccourse.ImportSchoolTypeLevelSpecCourseService;
import com.allen.util.ExcelUtil;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/12.
 */
@Service
public class ImportSchoolTypeLevelSpecCourseServiceImpl implements ImportSchoolTypeLevelSpecCourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private AddSchoolTypeLevelSpecCourseService addSchoolTypeLevelSpecCourseService;
    @Autowired
    private SchoolTypeLevelSpecDao schoolTypeLevelSpecDao;

    @Override
    public JSONObject importCourse(HttpServletRequest request, long stlsId) throws Exception {
        JSONObject returnJSON = new JSONObject();
        String msg = "";
        Map<String, String> codeNameMap = new HashMap<String, String>();
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

            msg = readXls(inputStream, is2007, codeNameMap, msg, stlsId);
            returnJSON.put("msg", msg);
            if(StringUtil.isEmpty(msg)){
                for (Map.Entry<String, String> entry : codeNameMap.entrySet()) {
                    String code = entry.getKey();
                    String name = entry.getValue();
                    SchoolTypeLevelSpecCourse schoolTypeLevelSpecCourse = new SchoolTypeLevelSpecCourse();
                    schoolTypeLevelSpecCourse.setSchoolTypeLevelSpecId(stlsId);
                    schoolTypeLevelSpecCourse.setOperator(UserUtil.getLoginUserForName(request));
                    addSchoolTypeLevelSpecCourseService.add(schoolTypeLevelSpecCourse, code, name);
                }
                returnJSON.put("state", 0);
            }else{
                returnJSON.put("state", 1);
            }
        }
        return returnJSON;
    }


    protected String readXls(InputStream inputStream, boolean is2007, Map<String, String> codeNameMap, String msg, long stlsId)throws Exception{
        List<JSONObject> list = new ArrayList<JSONObject>();
        SchoolTypeLevelSpec schoolTypeLevelSpec = schoolTypeLevelSpecDao.findOne(stlsId);
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
            // 循环行Row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                JSONObject json = new JSONObject();
                Row row = sheet.getRow(rowNum);
                String code = row.getCell((short)0).toString();
                String name = row.getCell((short)1).toString();

                if(StringUtil.isEmpty(code)){
                    msg += "第"+rowNum+"行：课程编号为空<br />";
                }
                if(StringUtil.isEmpty(name)){
                    msg += "第"+rowNum+"行：课程名称为空<br />";
                }

                String tempName = codeNameMap.get(code);
                if(!StringUtil.isEmpty(tempName)){
                    msg += "第"+rowNum+"行：课程编号在文件里面有重复<br />";
                }

                Course course = courseDao.findByCodeAndSchoolId(code, schoolTypeLevelSpec.getSchoolId());
                if(null != course && !course.getName().equals(name)){
                    msg += "第"+rowNum+"行：课程编号已经在系统里面存在，名称为"+course.getName()+"<br />";
                }

                if(!StringUtil.isEmpty(code)) {
                    codeNameMap.put(code, name);
                }
                list.add(json);
            }
        }
        return msg;
    }
}
