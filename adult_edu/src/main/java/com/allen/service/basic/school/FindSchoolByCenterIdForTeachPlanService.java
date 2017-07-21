package com.allen.service.basic.school;

import com.allen.entity.basic.School;

import java.util.List;

/**
 * Created by Allen on 2017/7/21.
 */
public interface FindSchoolByCenterIdForTeachPlanService {
    public List<School> find(long centerId)throws Exception;
}
