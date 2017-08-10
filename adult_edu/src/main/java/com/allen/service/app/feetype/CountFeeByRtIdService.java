package com.allen.service.app.feetype;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/10.
 */
public interface CountFeeByRtIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
