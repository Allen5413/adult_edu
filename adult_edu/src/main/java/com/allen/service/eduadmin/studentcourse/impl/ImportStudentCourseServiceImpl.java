package com.allen.service.eduadmin.studentcourse.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.course.CourseDao;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.eduadmin.studentcourse.FindStudentCourseDao;
import com.allen.dao.eduadmin.studentcourse.StudentCourseDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.basic.Course;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.Spec;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.StudentCourse;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.fee.StudentFee;
import com.allen.entity.recruit.SignUp;
import com.allen.service.eduadmin.student.AddStudentService;
import com.allen.service.eduadmin.student.ImportStudentService;
import com.allen.service.eduadmin.studentcourse.ImportStudentCourseService;
import com.allen.util.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/12.
 */
@Service
public class ImportStudentCourseServiceImpl implements ImportStudentCourseService {

    @Autowired
    private StudentCourseDao studentCourseDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CourseDao courseDao;

    @Override
    @Transactional
    public JSONObject importScore(HttpServletRequest request, long schoolId) throws Exception {
        JSONObject returnJSON = new JSONObject();
        String msg = "";
        Map<Long, Map<Long, Integer>> codeMap = new HashMap<Long, Map<Long, Integer>>();
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

            msg = readXls(inputStream, is2007, codeMap, msg, schoolId);
            returnJSON.put("msg", msg);
            if(StringUtil.isEmpty(msg)){
                for (Map.Entry<Long, Map<Long, Integer>> entry : codeMap.entrySet()) {
                    long studentId = entry.getKey();
                    Map<Long, Integer> courseMap = entry.getValue();
                    if(null != courseMap){
                        for (Map.Entry<Long, Integer> entry2 : courseMap.entrySet()) {
                            Long courseId = entry2.getKey();
                            Integer score = entry2.getValue();

                            StudentCourse studentCourse = studentCourseDao.findByStudentIdAndCourseId(studentId, courseId);
                            if(null != studentCourse){
                                studentCourse.setScore(score);
                                studentCourse.setOperator(UserUtil.getLoginUserForName(request));
                                studentCourse.setOperateTime(DateUtil.getLongNowTime());
                            }else{
                                studentCourse = new StudentCourse();
                                studentCourse.setStudentId(studentId);
                                studentCourse.setCourseId(courseId);
                                studentCourse.setScore(score);
                                studentCourse.setOperator(UserUtil.getLoginUserForName(request));
                            }
                            studentCourseDao.save(studentCourse);
                        }
                    }
                }
                returnJSON.put("state", 0);
            }else{
                returnJSON.put("state", 1);
            }
        }
        return returnJSON;
    }


    protected String readXls(InputStream inputStream, boolean is2007, Map<Long, Map<Long, Integer>> codeMap, String msg, long schoolId)throws Exception{
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
                String studentCode = row.getCell((short)0).toString().trim();
                String courseCode = row.getCell((short)1).toString().trim();
                String score = row.getCell((short)2).toString().trim();

                if(StringUtil.isEmpty(studentCode)){
                    msg += "第"+rowNum+"行：学号为空<br />";
                }
                if(StringUtil.isEmpty(courseCode)){
                    msg += "第"+rowNum+"行：课程编号为空<br />";
                }
                if(StringUtil.isEmpty(score)){
                    msg += "第"+rowNum+"行：成绩为空<br />";
                }

                Student student = studentDao.findBySchoolIdAndCode(schoolId, studentCode);
                if(null == student){
                    msg += "第" + rowNum + "行：学号"+studentCode+"没有在所选学校里面找到<br />";
                }
                Course course = courseDao.findByCodeAndSchoolId(courseCode, schoolId);
                if(null == course){
                    msg += "第" + rowNum + "行：课程编号"+courseCode+"没有在所选学校里面找到<br />";
                }
                if(!"通过".equals(score) && !"未通过".equals(score)){
                    msg += "第" + rowNum + "行：成绩格式录入错误，只能填通过或者未通过<br />";
                }

                if(null != student && null != course) {
                    Map<Long, Integer> courseScoreMap = codeMap.get(student.getId());
                    if (null != courseScoreMap) {
                        Integer score2 = courseScoreMap.get(course.getId());
                        if (null != score2) {
                            msg += "第" + rowNum + "行：学号" + studentCode + "的课程编号" + courseCode + "的成绩在文件里面有重复<br />";
                        } else {
                            courseScoreMap.put(course.getId(), "通过".equals(score) ? StudentCourse.SCORE_PASS : StudentCourse.SCORE_NOT);
                            codeMap.put(student.getId(), courseScoreMap);
                        }
                    } else {
                        courseScoreMap = new HashMap<Long, Integer>();
                        courseScoreMap.put(course.getId(), "通过".equals(score) ? StudentCourse.SCORE_PASS : StudentCourse.SCORE_NOT);
                        codeMap.put(student.getId(), courseScoreMap);
                    }
                }
            }
        }
        return msg;
    }
}
