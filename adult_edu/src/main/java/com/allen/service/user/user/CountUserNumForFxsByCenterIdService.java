package com.allen.service.user.user;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/25.
 */
public interface CountUserNumForFxsByCenterIdService {
    public BigInteger find(long centerId)throws Exception;
}
