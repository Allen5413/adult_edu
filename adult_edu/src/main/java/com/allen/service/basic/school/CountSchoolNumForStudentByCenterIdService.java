package com.allen.service.basic.school;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/22.
 */
public interface CountSchoolNumForStudentByCenterIdService {
    public BigInteger find(long centerId)throws Exception;
}
