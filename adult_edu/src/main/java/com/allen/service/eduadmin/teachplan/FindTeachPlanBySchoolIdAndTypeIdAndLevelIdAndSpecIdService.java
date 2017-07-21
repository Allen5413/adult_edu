package com.allen.service.eduadmin.teachplan;

import com.allen.entity.eduadmin.TeachPlan;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
public interface FindTeachPlanBySchoolIdAndTypeIdAndLevelIdAndSpecIdService {
    public List<TeachPlan> find(long schoolId, long typeId, long levelId, long specId)throws Exception;
}
