package com.allen.service.eduadmin.teachplan;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/23.
 */
public interface CountTeachPlanNumForYearANdTermByCenterIdService {
    public BigInteger find(long centerId)throws Exception;
}
