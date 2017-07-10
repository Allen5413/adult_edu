package com.allen.dao.basic.schooltypelevelspeccourse;

import com.allen.entity.basic.SchoolTypeLevelSpecCourse;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/7/7.
 */
public interface SchoolTypeLevelSpecCourseDao extends CrudRepository<SchoolTypeLevelSpecCourse,Long> {
    public SchoolTypeLevelSpecCourse findBySchoolTypeLevelSpecId(long schoolTypeLevelSpecId)throws Exception;
}
