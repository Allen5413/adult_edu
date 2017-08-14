package com.allen.service.app.center;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/14.
 */
public interface FindCByIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
