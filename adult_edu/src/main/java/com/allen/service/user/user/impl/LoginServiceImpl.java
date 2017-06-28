package com.allen.service.user.user.impl;

import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.LoginService;
import com.allen.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginName, String pwd) throws Exception {
        pwd = MD5Util.MD5(pwd);
        return userDao.findByLoginNameAndPwd(loginName, pwd);
    }
}
