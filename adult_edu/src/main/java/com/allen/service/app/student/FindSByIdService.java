package com.allen.service.app.student;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/11.
 */
public interface FindSByIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
