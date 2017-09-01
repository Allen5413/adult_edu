package com.allen.service.eduadmin.teachplan.impl;

import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.service.eduadmin.teachplan.FindTeachPlanBySchoolIdAndSpecIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/8/31.
 */
@Service
public class FindTeachPlanBySchoolIdAndSpecIdServiceImpl implements FindTeachPlanBySchoolIdAndSpecIdService {

    @Autowired
    private TeachPlanDao teachPlanDao;

    @Override
    public List<TeachPlan> find(long schoolId, long specId) throws Exception {
        return teachPlanDao.findBySchoolIdAndSpecIdOrderByYearDescTermDesc(schoolId, specId);
    }
}
