package com.allen.service.eduadmin.student;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/24.
 */
public interface CountStudentNumByCenterIdService {
    public BigInteger find(long centerId)throws Exception;
}
