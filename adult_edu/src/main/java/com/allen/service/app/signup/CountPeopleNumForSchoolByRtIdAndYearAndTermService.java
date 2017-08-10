package com.allen.service.app.signup;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/10.
 */
public interface CountPeopleNumForSchoolByRtIdAndYearAndTermService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
