package com.allen.service.basic.spec.impl;

import com.allen.dao.basic.spec.FindSpecDao;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/7.
 */
@Service
public class FindSpecBySchoolIdAndTypeIdAndLevelIdServiceIMpl implements FindSpecBySchoolIdAndTypeIdAndLevelIdService {

    @Autowired
    private FindSpecDao findSpecDao;

    @Override
    public List<Map> find(long schoolId, long typeId, long levelId) throws Exception {
        return findSpecDao.findBySchoolIdAndTypeIdAndLevelId(schoolId, typeId, levelId);
    }
}
