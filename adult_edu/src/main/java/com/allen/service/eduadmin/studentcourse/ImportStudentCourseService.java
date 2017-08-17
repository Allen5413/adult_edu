package com.allen.service.eduadmin.studentcourse;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
public interface ImportStudentCourseService {
    public JSONObject importScore(HttpServletRequest request, long schoolId)throws Exception;
}
