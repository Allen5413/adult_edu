package com.allen.service.basic.course;

import com.allen.entity.basic.Course;

/**
 * Created by Allen on 2017/7/10.
 */
public interface FindCourseByIdService {
    public Course find(long id)throws Exception;
}
