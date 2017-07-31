package com.allen.service.eduadmin.student;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
public interface ImportStudentService {
    public JSONObject importStudent(HttpServletRequest request, long schoolId)throws Exception;
}
