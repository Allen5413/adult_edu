package com.allen.service.eduadmin.recruittype;

import com.allen.entity.eduadmin.RecruitType;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
public interface FindRecruitTypeBySchoolIdForTeachPlanService {
    public List<RecruitType> find(long schoolId)throws Exception;
}
