package com.allen.service.app.notify;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/9/13.
 */
public interface FindNByIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
