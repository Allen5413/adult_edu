package com.allen.web.controller.eduadmin.studentcourse;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.basic.Course;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.StudentCourse;
import com.allen.service.basic.course.FindCourseByIdService;
import com.allen.service.eduadmin.recruittype.AddRecruitTypeService;
import com.allen.service.eduadmin.student.FindStudentByIdService;
import com.allen.service.eduadmin.studentcourse.AddStudentCourseService;
import com.allen.service.eduadmin.studentcourse.FindStudentCourseByStudentIdAndCourseIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addStudentCourse")
public class AddStudentCourseController extends BaseController {

    @Autowired
    private AddStudentCourseService addStudentCourseService;
    @Autowired
    private FindStudentByIdService findStudentByIdService;
    @Autowired
    private FindCourseByIdService findCourseByIdService;
    @Autowired
    private FindStudentCourseByStudentIdAndCourseIdService findStudentCourseByStudentIdAndCourseIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, long studentId, long courseId,
                       @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        Student student = findStudentByIdService.find(studentId);
        Course course = findCourseByIdService.find(courseId);
        StudentCourse studentCourse = findStudentCourseByStudentIdAndCourseIdService.find(studentId, courseId);
        if(null == student){
            throw new BusinessException("没有找到学生信息");
        }
        if(null == course){
            throw new BusinessException("没有找到课程信息");
        }
        request.setAttribute("student", student);
        request.setAttribute("course", course);
        request.setAttribute("studentCourse", studentCourse);
        request.setAttribute("reqParams", new String(reqParams.getBytes("iso-8859-1"), "gbk"));
        return "eduadmin/studentcourse/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, StudentCourse studentCourse) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != studentCourse) {
            studentCourse.setOperator(UserUtil.getLoginUserForName(request));
            addStudentCourseService.add(studentCourse);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
