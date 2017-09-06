package com.allen.service.eduadmin.teachplancourse.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.course.CourseDao;
import com.allen.dao.eduadmin.studentcourse.StudentCourseDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.eduadmin.teachplancourse.TeachPlanCourseDao;
import com.allen.entity.basic.Course;
import com.allen.entity.eduadmin.StudentCourse;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.eduadmin.TeachPlanCourse;
import com.allen.service.eduadmin.teachplancourse.AddTeachPlanCourseService;
import com.allen.service.eduadmin.teachplancourse.ImportTeachPlanCourseService;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class ImportTeachPlanCourseServiceImpl implements ImportTeachPlanCourseService {

    @Autowired
    private TeachPlanDao teachPlanDao;
    @Autowired
    private TeachPlanCourseDao teachPlanCourseDao;
    @Autowired
    private StudentCourseDao studentCourseDao;
    @Autowired
    private AddTeachPlanCourseService addTeachPlanCourseService;
    @Autowired
    private CourseDao courseDao;

    @Override
    @Transactional
    public JSONObject importCourse(HttpServletRequest request, long id) throws Exception {
        List<StudentCourse> studentCourseList = studentCourseDao.findByTeachPlanId(id);
        if(null != studentCourseList && 0 < studentCourseList.size()){
            throw new BusinessException("该批次下已经有学生录入了课程成绩了，不能再重新导入课程！");
        }
        //删除掉之前的课程信息
        teachPlanCourseDao.delByTeachPlanId(id);

        //得到批次信息
        TeachPlan teachPlan = teachPlanDao.findOne(id);

        JSONObject returnJSON = new JSONObject();
        String msg = "";
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

            List<JSONObject> courseList = new ArrayList<JSONObject>();
            msg = readXls(inputStream, is2007, msg, teachPlan.getSchoolId(), courseList);
            returnJSON.put("msg", msg);
            if(StringUtil.isEmpty(msg)){
                for (JSONObject jsonObject : courseList) {
                    TeachPlanCourse teachPlanCourse = new TeachPlanCourse();
                    teachPlanCourse.setTeachPlanId(teachPlan.getId());
                    teachPlanCourse.setSemester(jsonObject.get("semester").toString());
                    teachPlanCourse.setType(jsonObject.get("type").toString());
                    teachPlanCourse.setCourseDate(jsonObject.get("courseDate").toString());
                    teachPlanCourse.setScore(jsonObject.get("score").toString());
                    teachPlanCourse.setCerator(UserUtil.getLoginUserForName(request));
                    teachPlanCourse.setOperator(UserUtil.getLoginUserForName(request));
                    addTeachPlanCourseService.add(teachPlanCourse, jsonObject.get("code").toString(), jsonObject.get("name").toString());
                }
                returnJSON.put("state", 0);
            }else{
                teachPlanDao.delete(teachPlan.getId());
                returnJSON.put("state", 1);
            }
        }
        return returnJSON;
    }

    protected String readXls(InputStream inputStream, boolean is2007, String msg, long schoolId, List<JSONObject> courseList)throws Exception{
        Map<String, Map<String, String>> semesterCodeNameMap = new HashMap<String, Map<String, String>>();
        Map<String, String> codeNameMap = new HashMap<String, String>();
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

            if(sheet.getLastRowNum() < 2){
                msg += "上传文件里面没有数据";
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                JSONObject json = new JSONObject();
                Row row = sheet.getRow(rowNum);
                String semester = row.getCell((short)0).toString();
                String code = row.getCell((short)1).toString();
                String name = row.getCell((short)2).toString();
                String type = row.getCell((short)3).toString();
                String courseDate = row.getCell((short)4).toString();
                String score = row.getCell((short) 5).toString();

                if(StringUtil.isEmpty(semester)){
                    msg += "第"+rowNum+"行：学期为空<br />";
                }
                if(StringUtil.isEmpty(code)){
                    msg += "第"+rowNum+"行：课程编号为空<br />";
                }
                if(StringUtil.isEmpty(name)){
                    msg += "第"+rowNum+"行：课程名称为空<br />";
                }
                if(StringUtil.isEmpty(type)){
                    msg += "第"+rowNum+"行：考核方式为空<br />";
                }
                if(StringUtil.isEmpty(courseDate)){
                    msg += "第"+rowNum+"行：开课时间为空<br />";
                }
                if(StringUtil.isEmpty(score)){
                    msg += "第"+rowNum+"行：学分为空<br />";
                }


                String tempName = codeNameMap.get(code);
                if (!StringUtil.isEmpty(tempName) && !tempName.equals(name)) {
                    msg += "第" + rowNum + "行：课程编号在文件里已经存在，名称为"+tempName+"<br />";
                }

                Map<String, String> codeNameMap2 = semesterCodeNameMap.get(semester);
                if(null != codeNameMap2) {
                    String tempName2 = codeNameMap2.get(code);
                    if (!StringUtil.isEmpty(tempName2) && tempName2.equals(name)) {
                        msg += "第" + rowNum + "行：课程在"+semester+"里面有重复<br />";
                    }
                }

                Course course = courseDao.findByCodeAndSchoolId(code, schoolId);
                if(null != course && !course.getName().equals(name)){
                    msg += "第"+rowNum+"行：课程编号已经在系统里面存在，名称为"+course.getName()+"<br />";
                }

                if(!StringUtil.isEmpty(code)) {
                    codeNameMap.put(code, name);
                }
                if(!StringUtil.isEmpty(semester)) {
                    if(!StringUtil.isEmpty(code)) {
                        Map<String,String> codeNameMap3 = semesterCodeNameMap.get(semester);
                        if(null == codeNameMap3){
                            codeNameMap3 = new HashMap<String, String>();
                        }
                        codeNameMap3.put(code, name);
                        semesterCodeNameMap.put(semester, codeNameMap3);
                    }
                }

                json.put("semester", semester);
                json.put("code", code);
                json.put("name", name);
                json.put("type", type);
                json.put("courseDate", courseDate);
                json.put("score", score);
                courseList.add(json);
            }
        }
        return msg;
    }
}
