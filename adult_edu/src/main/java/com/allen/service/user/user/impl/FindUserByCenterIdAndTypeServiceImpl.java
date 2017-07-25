package com.allen.service.user.user.impl;

import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.FindUserByCenterIdAndTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/7/25 0025.
 */
@Service
public class FindUserByCenterIdAndTypeServiceImpl implements FindUserByCenterIdAndTypeService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> find(long centerId, int type) throws Exception {
        return userDao.findByCenterIdAndTypeAndState(centerId, type, User.STATE_ENABLE);
    }
}
