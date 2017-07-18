package com.allen.service.user.user.impl;

import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.FindUserByCenterIdForCenterManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/18.
 */
@Service
public class FindUserByCenterIdForCenterManServiceImpl implements FindUserByCenterIdForCenterManService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> find(long centerId) throws Exception {
        return userDao.findByCenterIdForCenterMan(centerId);
    }
}
