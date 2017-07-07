package com.allen.service.basic.schooltypelevelspec;

import com.allen.entity.basic.SchoolTypeLevelSpec;

/**
 * Created by Allen on 2017/7/7.
 */
public interface AddSchoolTypeLevelSpecService {
    public void add(SchoolTypeLevelSpec schoolTypeLevelSpec, String specCode, String specName)throws Exception;
}
