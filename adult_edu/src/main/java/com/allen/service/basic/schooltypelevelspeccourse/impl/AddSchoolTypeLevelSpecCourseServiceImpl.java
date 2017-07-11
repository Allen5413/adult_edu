package com.allen.service.basic.schooltypelevelspeccourse.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.course.CourseDao;
import com.allen.dao.basic.schooltypelevelspec.SchoolTypeLevelSpecDao;
import com.allen.dao.basic.schooltypelevelspeccourse.SchoolTypeLevelSpecCourseDao;
import com.allen.entity.basic.Course;
import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.entity.basic.SchoolTypeLevelSpecCourse;
import com.allen.service.basic.course.AddCourseService;
import com.allen.service.basic.schooltypelevelspeccourse.AddSchoolTypeLevelSpecCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class AddSchoolTypeLevelSpecCourseServiceImpl implements AddSchoolTypeLevelSpecCourseService {

    @Autowired
    private SchoolTypeLevelSpecCourseDao schoolTypeLevelSpecCourseDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private AddCourseService addCourseService;
    @Autowired
    private SchoolTypeLevelSpecDao schoolTypeLevelSpecDao;

    @Override
    @Transactional
    public void add(SchoolTypeLevelSpecCourse schoolTypeLevelSpecCourse, String courseCode, String courseName) throws Exception {
        SchoolTypeLevelSpec schoolTypeLevelSpec = schoolTypeLevelSpecDao.findOne(schoolTypeLevelSpecCourse.getSchoolTypeLevelSpecId());
        Course course = courseDao.findByCodeAndSchoolId(courseCode, schoolTypeLevelSpec.getSchoolId());
        if(null != course){
            if(!course.getName().equals(courseName)) {
                throw new BusinessException("该编号的课程已经存在，名为：" + course.getName());
            }
            SchoolTypeLevelSpecCourse schoolTypeLevelSpecCourse2 = schoolTypeLevelSpecCourseDao.findBySchoolTypeLevelSpecIdAndCourseId(schoolTypeLevelSpecCourse.getSchoolTypeLevelSpecId(), course.getId());
            if(null == schoolTypeLevelSpecCourse2) {
                schoolTypeLevelSpecCourse.setCourseId(course.getId());
            }
        }else{
            course = new Course();
            course.setSchoolId(schoolTypeLevelSpec.getSchoolId());
            course.setCode(courseCode);
            course.setName(courseName);
            course.setCreator(schoolTypeLevelSpec.getOperator());
            course.setOperator(schoolTypeLevelSpec.getOperator());
            addCourseService.add(course);
            schoolTypeLevelSpecCourse.setCourseId(course.getId());
        }
        if(schoolTypeLevelSpecCourse.getCourseId() > 0) {
            schoolTypeLevelSpecCourseDao.save(schoolTypeLevelSpecCourse);
        }
    }
}
