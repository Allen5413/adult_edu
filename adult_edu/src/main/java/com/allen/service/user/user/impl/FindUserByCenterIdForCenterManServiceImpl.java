package com.allen.service.user.user.impl;

import com.allen.dao.user.user.FindUserDao;
import com.allen.service.user.user.FindUserByCenterIdForCenterManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/18.
 */
@Service
public class FindUserByCenterIdForCenterManServiceImpl implements FindUserByCenterIdForCenterManService {

    @Autowired
    private FindUserDao findUserDao;

    @Override
    public List<Map> find(long centerId) throws Exception {
        return findUserDao.findByCenterIdForCenterMan(centerId);
    }
}
