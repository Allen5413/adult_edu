package com.allen.service.app.datachange;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/14.
 */
public interface AuditDCService {
    public JSONObject audit(HttpServletRequest request)throws Exception;
}
