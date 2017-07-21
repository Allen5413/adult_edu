package com.allen.service.basic.spec.impl;

import com.allen.dao.basic.spec.SpecDao;
import com.allen.entity.basic.Spec;
import com.allen.service.basic.spec.FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
@Service
public class FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanServiceImpl implements FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService {

    @Autowired
    private SpecDao specDao;

    @Override
    public List<Spec> find(long schoolId, long typeId, long levelId) throws Exception {
        return specDao.findBySchoolIdAndTypeIdAndLevelIdForTeachPlan(schoolId, typeId, levelId);
    }
}
