package com.allen.dao.basic.schooltypelevelspec;

import com.allen.entity.basic.SchoolTypeLevelSpec;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/7/7.
 */
public interface SchoolTypeLevelSpecDao extends CrudRepository<SchoolTypeLevelSpec ,Long> {
    public SchoolTypeLevelSpec findBySchoolIdAndRecruitTypeIdAndLevelIdAndSpecId(long schoolId, long typeId, long levelId, long specId)throws Exception;
}
