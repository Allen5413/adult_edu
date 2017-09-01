package com.allen.service.eduadmin.teachplan;

import com.allen.entity.eduadmin.TeachPlan;

import java.util.List;

/**
 * Created by Allen on 2017/8/31.
 */
public interface FindTeachPlanBySchoolIdAndSpecIdService {
    public List<TeachPlan> find(long schoolId, long specId)throws Exception;
}
