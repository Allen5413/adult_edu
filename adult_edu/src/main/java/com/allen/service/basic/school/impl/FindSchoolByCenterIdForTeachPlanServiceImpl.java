package com.allen.service.basic.school.impl;

import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.FindSchoolByCenterIdForTeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
@Service
public class FindSchoolByCenterIdForTeachPlanServiceImpl implements FindSchoolByCenterIdForTeachPlanService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public List<School> find(long centerId) throws Exception {
        return schoolDao.findByCenterIdForTeachPlan(centerId);
    }
}
