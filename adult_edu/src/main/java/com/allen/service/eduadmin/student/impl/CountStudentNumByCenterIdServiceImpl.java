package com.allen.service.eduadmin.student.impl;

import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.service.eduadmin.student.CountStudentNumByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
public class CountStudentNumByCenterIdServiceImpl implements CountStudentNumByCenterIdService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public BigInteger find(long centerId) throws Exception {
        return studentDao.countNumByCenterId(centerId);
    }
}
