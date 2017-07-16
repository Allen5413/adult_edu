package com.allen.service.eduadmin.teachplan;

import com.allen.entity.eduadmin.TeachPlan;

/**
 * Created by Allen on 2017/7/16 0016.
 */
public interface FindTeachPlanByIdService {
    public TeachPlan find(long id)throws Exception;
}
