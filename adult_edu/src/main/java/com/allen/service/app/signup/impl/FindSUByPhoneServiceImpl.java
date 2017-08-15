package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.recruit.signup.FindSignUpDao;
import com.allen.service.app.signup.FindSUByPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/8/15.
 */
@Service
public class FindSUByPhoneServiceImpl implements FindSUByPhoneService {

    @Autowired
    private FindSignUpDao findSignUpDao;

    @Override
    public JSONObject find(String phone) throws Exception {
        JSONObject json = new JSONObject();
        json.put("list", findSignUpDao.findByPhone(phone));
        json.put("status", 1);
        return json;
    }
}
