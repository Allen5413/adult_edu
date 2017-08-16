package com.allen.service.app.studentfee;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/16.
 */
public interface FindSFInfoByStudentIdService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
