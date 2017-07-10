package com.allen.service.basic.schooltypelevelspec;

import com.allen.entity.basic.SchoolTypeLevelSpec;

/**
 * Created by Allen on 2017/7/10.
 */
public interface FindSchoolTypeLevelSpecByIdService {
    public SchoolTypeLevelSpec find(long id)throws Exception;
}
