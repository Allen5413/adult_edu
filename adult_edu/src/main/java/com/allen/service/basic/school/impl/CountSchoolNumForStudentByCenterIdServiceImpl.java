package com.allen.service.basic.school.impl;

import com.allen.dao.basic.school.SchoolDao;
import com.allen.service.basic.school.CountSchoolNumForStudentByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/22.
 */
@Service
public class CountSchoolNumForStudentByCenterIdServiceImpl implements CountSchoolNumForStudentByCenterIdService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public BigInteger find(long centerId) throws Exception {
        return schoolDao.countNumForStudentByCenterId(centerId);
    }
}
