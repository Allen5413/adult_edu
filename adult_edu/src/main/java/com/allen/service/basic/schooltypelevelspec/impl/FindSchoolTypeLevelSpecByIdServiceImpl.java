package com.allen.service.basic.schooltypelevelspec.impl;

import com.allen.dao.basic.schooltypelevelspec.SchoolTypeLevelSpecDao;
import com.allen.entity.basic.SchoolTypeLevelSpec;
import com.allen.service.basic.schooltypelevelspec.FindSchoolTypeLevelSpecByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/10.
 */
@Service
public class FindSchoolTypeLevelSpecByIdServiceImpl implements FindSchoolTypeLevelSpecByIdService {

    @Autowired
    private SchoolTypeLevelSpecDao schoolTypeLevelSpecDao;

    @Override
    public SchoolTypeLevelSpec find(long id) throws Exception {
        return schoolTypeLevelSpecDao.findOne(id);
    }
}
