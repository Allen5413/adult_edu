package com.allen.service.app.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.app.user.FindUByPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Allen on 2017/8/9.
 */
@Service
public class FindUByPhoneServiceImpl implements FindUByPhoneService{

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject find(String phone) throws Exception {
        JSONObject json = new JSONObject();
        User user = userDao.findByPhone(phone);
        if(null == user){
            json.put("type", User.TYPE_STUDENT);
            json.put("phone", phone);
        }else{
            json.put("id", user.getId());
            json.put("name", user.getName());
            json.put("type", user.getType());
            json.put("address", user.getAddress());
            json.put("phone", user.getPhone());
            json.put("centerId", user.getCenterId());
        }
        json.put("status", 1);
        return json;
    }
}
