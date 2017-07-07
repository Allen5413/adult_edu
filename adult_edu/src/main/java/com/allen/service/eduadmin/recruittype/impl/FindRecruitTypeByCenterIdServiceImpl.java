package com.allen.service.eduadmin.recruittype.impl;

import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.service.eduadmin.recruittype.FindRecruitTypeByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/3.
 */
@Service
public class FindRecruitTypeByCenterIdServiceImpl implements FindRecruitTypeByCenterIdService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;

    @Override
    public List<RecruitType> find(long id) throws Exception {
        return recruitTypeDao.findByCenterId(id);
    }
}
