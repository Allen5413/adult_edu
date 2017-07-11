package com.allen.service.basic.schooltypelevelspeccourse.impl;

import com.allen.dao.basic.schooltypelevelspeccourse.SchoolTypeLevelSpecCourseDao;
import com.allen.entity.basic.SchoolTypeLevelSpecCourse;
import com.allen.service.basic.schooltypelevelspeccourse.FindSchoolTypeLevelSpecCourseByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/10.
 */
@Service
public class FindSchoolTypeLevelSpecCourseByIdServiceImpl implements FindSchoolTypeLevelSpecCourseByIdService {

    @Autowired
    private SchoolTypeLevelSpecCourseDao schoolTypeLevelSpecCourseDao;

    @Override
    public SchoolTypeLevelSpecCourse find(long id) throws Exception {
        return schoolTypeLevelSpecCourseDao.findOne(id);
    }
}
