package com.allen.service.eduadmin.studentcourse.impl;

import com.allen.dao.eduadmin.studentcourse.StudentCourseDao;
import com.allen.entity.eduadmin.StudentCourse;
import com.allen.service.eduadmin.studentcourse.FindStudentCourseByStudentIdAndCourseIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/8/21.
 */
@Service
public class FindStudentCourseByStudentIdAndCourseIdServiceImpl implements FindStudentCourseByStudentIdAndCourseIdService {

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Override
    public StudentCourse find(long studentId, long courseId) throws Exception {
        return studentCourseDao.findByStudentIdAndCourseId(studentId, courseId);
    }
}
