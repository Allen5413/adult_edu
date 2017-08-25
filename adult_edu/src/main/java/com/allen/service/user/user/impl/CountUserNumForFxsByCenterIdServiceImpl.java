package com.allen.service.user.user.impl;

import com.allen.dao.user.user.UserDao;
import com.allen.service.user.user.CountUserNumForFxsByCenterIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/8/25.
 */
@Service
public class CountUserNumForFxsByCenterIdServiceImpl implements CountUserNumForFxsByCenterIdService {

    @Autowired
    private UserDao userDao;

    @Override
    public BigInteger find(long centerId) throws Exception {
        return userDao.countNumForFxsByCenterId(centerId);
    }
}
