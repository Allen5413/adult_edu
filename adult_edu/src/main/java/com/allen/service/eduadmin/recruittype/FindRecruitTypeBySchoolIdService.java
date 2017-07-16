package com.allen.service.eduadmin.recruittype;

import com.allen.entity.eduadmin.RecruitType;

import java.util.List;

/**
 * Created by Allen on 2017/7/15 0015.
 */
public interface FindRecruitTypeBySchoolIdService {
    public List<RecruitType> find(long schoolId)throws Exception;
}
