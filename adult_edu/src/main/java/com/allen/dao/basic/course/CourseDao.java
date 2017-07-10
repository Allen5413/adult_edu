package com.allen.dao.basic.course;

import com.allen.entity.basic.Course;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/7/10.
 */
public interface CourseDao extends CrudRepository<Course, Long> {
    public Course findByCodeAndSchoolId(String code, long schoolId)throws Exception;
}
