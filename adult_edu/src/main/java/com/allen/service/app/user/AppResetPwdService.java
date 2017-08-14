package com.allen.service.app.user;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/29.
 */
public interface AppResetPwdService {
    public JSONObject edit(HttpServletRequest request)throws Exception;
}
