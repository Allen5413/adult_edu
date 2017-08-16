package com.allen.service.app.school;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/16.
 */
public interface FindScByCenterIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
