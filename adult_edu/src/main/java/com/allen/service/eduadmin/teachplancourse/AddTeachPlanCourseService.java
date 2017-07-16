package com.allen.service.eduadmin.teachplancourse;

import com.allen.entity.eduadmin.TeachPlanCourse;

/**
 * Created by Allen on 2017/7/7.
 */
public interface AddTeachPlanCourseService {
    public TeachPlanCourse add(TeachPlanCourse teachPlanCourse, String code, String name)throws Exception;
}
