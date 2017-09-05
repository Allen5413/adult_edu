package com.allen.service.basic.course;

import com.allen.entity.basic.Course;

import java.util.List;

/**
 * Created by Allen on 2017/9/5.
 */
public interface FindCourseBySpecIdService {
    public List<Course> find(long specId)throws Exception;
}
