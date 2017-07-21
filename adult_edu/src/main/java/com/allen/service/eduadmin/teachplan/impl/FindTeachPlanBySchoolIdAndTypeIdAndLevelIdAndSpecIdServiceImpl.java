package com.allen.service.eduadmin.teachplan.impl;

import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.service.eduadmin.teachplan.FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
@Service
public class FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdServiceImpl implements FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService {

    @Autowired
    private TeachPlanDao teachPlanDao;

    @Override
    public List<TeachPlan> find(long schoolId, long typeId, long levelId, long specId) throws Exception {
        return teachPlanDao.findBySchoolIdAndTypeIdAndLevelIdAndSpecId(schoolId, typeId, levelId, specId);
    }
}
