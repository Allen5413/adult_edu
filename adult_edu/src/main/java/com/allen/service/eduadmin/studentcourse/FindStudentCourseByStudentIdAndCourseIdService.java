package com.allen.service.eduadmin.studentcourse;

import com.allen.entity.eduadmin.StudentCourse;

/**
 * Created by Allen on 2017/8/21.
 */
public interface FindStudentCourseByStudentIdAndCourseIdService {
    public StudentCourse find(long studentId, long courseId)throws Exception;
}
