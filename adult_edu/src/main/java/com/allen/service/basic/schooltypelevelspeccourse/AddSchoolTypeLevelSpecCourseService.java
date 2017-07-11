package com.allen.service.basic.schooltypelevelspeccourse;

import com.allen.entity.basic.SchoolTypeLevelSpecCourse;

/**
 * Created by Allen on 2017/7/7.
 */
public interface AddSchoolTypeLevelSpecCourseService {
    public void add(SchoolTypeLevelSpecCourse schoolTypeLevelSpecCourse, String courseCode, String courseName)throws Exception;
}
