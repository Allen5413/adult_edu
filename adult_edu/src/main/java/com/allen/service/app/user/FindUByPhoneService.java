package com.allen.service.app.user;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/10.
 */
public interface FindUByPhoneService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
