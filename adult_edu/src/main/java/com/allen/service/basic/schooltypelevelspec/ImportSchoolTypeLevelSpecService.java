package com.allen.service.basic.schooltypelevelspec;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
public interface ImportSchoolTypeLevelSpecService {
    public JSONObject importSpec(HttpServletRequest request, long schoolId, long typeId, long levelId)throws Exception;
}
