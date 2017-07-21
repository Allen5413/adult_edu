package com.allen.service.eduadmin.recruittype.impl;

import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeBySchoolIdForTeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
@Service
public class FindRecruitTypeBySchoolIdForTeachPlanServiceImpl implements FindRecruitTypeBySchoolIdForTeachPlanService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;

    @Override
    public List<RecruitType> find(long schoolId) throws Exception {
        return recruitTypeDao.findBySchoolIdForTeachPlan(schoolId);
    }
}
