package com.allen.service.eduadmin.studentcourse.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.eduadmin.teachplancourse.TeachPlanCourseDao;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.eduadmin.TeachPlanCourse;
import com.allen.service.eduadmin.studentcourse.FindSCDetailByStudentIdAndCourseIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindSCDetailByStudentIdAndCourseIdServiceImpl implements FindSCDetailByStudentIdAndCourseIdService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeachPlanCourseDao teachPlanCourseDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String courseId = request.getParameter("courseId");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入学生id");
        }
        if(StringUtil.isEmpty(courseId)){
            throw new BusinessException("没有传入课程id");
        }
        Student student = studentDao.findOne(Long.parseLong(id));
        TeachPlanCourse teachPlanCourse = teachPlanCourseDao.findByTeachPlanIdAndCourseId(student.getTeachPlanId(), Long.parseLong(courseId));
        jsonObject.put("xf", teachPlanCourse.getScore());
        jsonObject.put("courseDate", teachPlanCourse.getCourseDate());
        jsonObject.put("type", teachPlanCourse.getType());
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
