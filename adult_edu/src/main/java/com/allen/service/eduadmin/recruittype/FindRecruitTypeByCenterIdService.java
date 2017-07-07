package com.allen.service.eduadmin.recruittype;

import com.allen.entity.eduadmin.RecruitType;

import java.util.List;

/**
 * Created by Allen on 2017/6/28.
 */
public interface FindRecruitTypeByCenterIdService {
    public List<RecruitType> find(long id)throws Exception;
}
