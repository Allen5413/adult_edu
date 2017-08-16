package com.allen.service.app.level;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/16.
 */
public interface FindLByCenterIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
