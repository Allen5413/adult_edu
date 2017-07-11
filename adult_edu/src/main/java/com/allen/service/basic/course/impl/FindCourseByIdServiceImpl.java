package com.allen.service.basic.course.impl;

import com.allen.dao.basic.course.CourseDao;
import com.allen.entity.basic.Course;
import com.allen.service.basic.course.FindCourseByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/10.
 */
@Service
public class FindCourseByIdServiceImpl implements FindCourseByIdService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public Course find(long id) throws Exception {
        return courseDao.findOne(id);
    }
}
