package com.allen.service.app.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.app.user.DelUService;
import com.allen.util.DateUtil;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/14.
 */
@Service
public class DelUServiceImpl implements DelUService {

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject del(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入用户id");
        }
        if(StringUtil.isEmpty(name)){
            throw new BusinessException("没有传入操作人");
        }
        User user = userDao.findOne(Long.parseLong(id));
        user.setState(User.STATE_DELETE);
        user.setOperator(name);
        user.setOperateTime(DateUtil.getLongNowTime());
        userDao.save(user);
        json.put("status", 1);
        return json;
    }
}
