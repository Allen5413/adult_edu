package com.allen.service.app.signup;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Allen on 2017/8/15.
 */
public interface FindSUByPhoneService {
    public JSONObject find(String phone)throws Exception;
}
