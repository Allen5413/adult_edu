package com.allen.service.user.user.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.EditPwdService;
import com.allen.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/7/18.
 */
@Service
public class EditPwdServiceImpl implements EditPwdService {

    @Autowired
    private UserDao userDao;

    @Override
    public void edit(String oldPwd, String newPwd, long id) throws Exception {
        User user = userDao.findOne(id);
        if(!user.getPwd().equals(MD5Util.MD5(oldPwd))){
            throw new BusinessException("旧密码错误");
        }
        user.setPwd(MD5Util.MD5(newPwd));
        userDao.save(user);
    }
}
