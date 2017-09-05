package com.allen.service.basic.course.impl;

import com.allen.dao.basic.course.CourseDao;
import com.allen.entity.basic.Course;
import com.allen.service.basic.course.FindCourseBySpecIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/9/5.
 */
@Service
public class FindCourseBySpecIdServiceImpl implements FindCourseBySpecIdService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> find(long specId) throws Exception {
        return courseDao.findBySpecId(specId);
    }
}
