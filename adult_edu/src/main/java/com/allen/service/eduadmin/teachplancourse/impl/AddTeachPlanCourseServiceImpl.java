package com.allen.service.eduadmin.teachplancourse.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.course.CourseDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.eduadmin.teachplancourse.TeachPlanCourseDao;
import com.allen.entity.basic.Course;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.eduadmin.TeachPlanCourse;
import com.allen.service.basic.course.AddCourseService;
import com.allen.service.eduadmin.teachplancourse.AddTeachPlanCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class AddTeachPlanCourseServiceImpl implements AddTeachPlanCourseService {

    @Autowired
    private TeachPlanDao teachPlanDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TeachPlanCourseDao teachPlanCourseDao;
    @Autowired
    private AddCourseService addCourseService;

    @Override
    public TeachPlanCourse add(TeachPlanCourse teachPlanCourse, String code, String name) throws Exception {
        TeachPlan teachPlan = teachPlanDao.findOne(teachPlanCourse.getTeachPlanId());
        if(null == teachPlan){
            throw new BusinessException("教学计划没有找到");
        }
        Course course = courseDao.findByCodeAndSchoolId(code, teachPlan.getSchoolId());
        if(null != course){
            if(!course.getName().equals(name)) {
                throw new BusinessException("该编号的课程已经存在，名为：" + course.getName());
            }
        }else{
            course = new Course();
            course.setSchoolId(teachPlan.getSchoolId());
            course.setCode(code);
            course.setName(name);
            course.setCreator(teachPlanCourse.getCerator());
            course.setOperator(teachPlanCourse.getOperator());
            addCourseService.add(course);
        }
        teachPlanCourse.setCourseId(course.getId());

        if(teachPlanCourse.getCourseId() > 0) {
            TeachPlanCourse teachPlanCourse2 = teachPlanCourseDao.findByTeachPlanIdAndSemesterAndCourseId(teachPlanCourse.getTeachPlanId(), teachPlanCourse.getSemester(), teachPlanCourse.getCourseId());
            if(null != teachPlanCourse2){
                throw new BusinessException("教学计划里面"+teachPlanCourse.getSemester()+"课程已经存在");
            }
            teachPlanCourseDao.save(teachPlanCourse);
        }
        return teachPlanCourse;
    }
}
