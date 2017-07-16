package com.allen.service.eduadmin.teachplan.impl;

import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.service.eduadmin.teachplan.FindTeachPlanByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/16 0016.
 */
@Service
public class FindTeachPlanByIdServiceImpl implements FindTeachPlanByIdService {

    @Autowired
    private TeachPlanDao teachPlanDao;

    @Override
    public TeachPlan find(long id) throws Exception {
        return teachPlanDao.findOne(id);
    }
}
