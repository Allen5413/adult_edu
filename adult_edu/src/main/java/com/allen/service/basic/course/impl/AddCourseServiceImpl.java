package com.allen.service.basic.course.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.course.CourseDao;
import com.allen.entity.basic.Course;
import com.allen.service.basic.course.AddCourseService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class AddCourseServiceImpl implements AddCourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public Course add(Course course) throws Exception {
        Course course2 = courseDao.findByCodeAndSchoolId(course.getCode(), course.getSchoolId());
        if(course2 != null && !StringUtil.isEmpty(course2.getName())){
            throw new BusinessException("课程编号已存在");
        }
        return courseDao.save(course);
    }
}
