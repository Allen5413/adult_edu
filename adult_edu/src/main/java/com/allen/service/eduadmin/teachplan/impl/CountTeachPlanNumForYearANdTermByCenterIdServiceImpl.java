package com.allen.service.eduadmin.teachplan.impl;

import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.service.eduadmin.teachplan.CountTeachPlanNumForYearANdTermByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/23.
 */
@Service
public class CountTeachPlanNumForYearANdTermByCenterIdServiceImpl implements CountTeachPlanNumForYearANdTermByCenterIdService {

    @Autowired
    private TeachPlanDao teachPlanDao;

    @Override
    public BigInteger find(long centerId) throws Exception {
        return teachPlanDao.countNumForYearANdTermByCenterId(centerId);
    }
}
