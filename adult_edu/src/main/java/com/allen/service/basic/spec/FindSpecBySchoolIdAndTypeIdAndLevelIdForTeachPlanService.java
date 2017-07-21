package com.allen.service.basic.spec;

import com.allen.entity.basic.Spec;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
public interface FindSpecBySchoolIdAndTypeIdAndLevelIdForTeachPlanService {
    public List<Spec> find(long schoolId, long typeId, long levelId)throws Exception;
}
