package com.allen.service.eduadmin.recruittype.impl;

import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/3.
 */
@Service
public class FindRecruitTypeByIdServiceImpl implements FindRecruitTypeByIdService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;

    @Override
    public RecruitType find(long id) throws Exception {
        return recruitTypeDao.findOne(id);
    }
}
