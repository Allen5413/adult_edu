package com.allen.service.app.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.app.user.AppResetPwdService;
import com.allen.util.MD5Util;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/14.
 */
@Service
public class AppResetPwdServiceImpl implements AppResetPwdService {

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject edit(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入用户id");
        }
        User user = userDao.findOne(Long.parseLong(id));
        String pwd = user.getPhone().substring(user.getPhone().length()-6, user.getPhone().length());
        user.setPwd(MD5Util.MD5(pwd));
        userDao.save(user);
        json.put("pwd", pwd);
        json.put("status", 1);
        return json;
    }
}
