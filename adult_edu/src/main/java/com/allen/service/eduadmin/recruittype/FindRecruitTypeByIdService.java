package com.allen.service.eduadmin.recruittype;

import com.allen.entity.eduadmin.RecruitType;

/**
 * Created by Allen on 2017/6/28.
 */
public interface FindRecruitTypeByIdService {
    public RecruitType find(long id)throws Exception;
}
