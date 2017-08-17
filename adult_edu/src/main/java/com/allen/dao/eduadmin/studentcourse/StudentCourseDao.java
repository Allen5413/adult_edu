package com.allen.dao.eduadmin.studentcourse;

import com.allen.entity.eduadmin.StudentCourse;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/8/17.
 */
public interface StudentCourseDao extends CrudRepository<StudentCourse, Long> {
    public StudentCourse findByStudentIdAndCourseId(long studentId, long courseId)throws Exception;
}
