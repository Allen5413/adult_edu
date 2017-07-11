package com.allen.service.basic.schooltypelevelspeccourse;

import com.allen.entity.basic.SchoolTypeLevelSpecCourse;

/**
 * Created by Allen on 2017/7/10.
 */
public interface FindSchoolTypeLevelSpecCourseByIdService {
    public SchoolTypeLevelSpecCourse find(long id)throws Exception;
}
