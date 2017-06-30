package com.allen.service.user.user.impl;

import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.ResetPwdService;
import com.allen.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/29.
 */
@Service
public class ResetPwdServiceImpl implements ResetPwdService {

    @Autowired
    private UserDao userDao;

    @Override
    public void edit(String loginName) throws Exception {
        User user = userDao.findByLoginName(loginName);
        user.setPwd(MD5Util.MD5("123456"));
        userDao.save(user);
    }
}
