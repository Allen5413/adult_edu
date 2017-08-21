package com.allen.service.eduadmin.studentcourse.impl;

import com.allen.dao.eduadmin.studentcourse.StudentCourseDao;
import com.allen.entity.eduadmin.StudentCourse;
import com.allen.service.eduadmin.studentcourse.AddStudentCourseService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class AddStudentCourseServiceImpl implements AddStudentCourseService {

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Override
    public void add(StudentCourse studentCourse) throws Exception {
        StudentCourse studentCourse2 = studentCourseDao.findByStudentIdAndCourseId(studentCourse.getStudentId(), studentCourse.getCourseId());
        if(null != studentCourse2){
            studentCourse2.setScore(studentCourse.getScore());
            studentCourse2.setOperator(studentCourse.getOperator());
            studentCourse2.setOperateTime(DateUtil.getLongNowTime());
            studentCourseDao.save(studentCourse2);
        }else {
            studentCourse.setOperateTime(DateUtil.getLongNowTime());
            studentCourseDao.save(studentCourse);
        }
    }
}
