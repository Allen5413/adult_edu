package com.allen.service.app.school;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/17.
 */
public interface FindScAndSpByCenterIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
