package com.allen.service.user.user.impl;

import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.FindUserByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/29.
 */
@Service
public class FindUserByIdServiceImpl implements FindUserByIdService {

    @Autowired
    private UserDao userDao;

    @Override
    public User find(long id) throws Exception {
        return userDao.findOne(id);
    }
}
