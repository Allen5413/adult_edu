package com.allen.service.basic.schooltypelevelspeccourse;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/7/12.
 */
public interface ImportSchoolTypeLevelSpecCourseService {
    public JSONObject importCourse(HttpServletRequest request, long stlsId)throws Exception;
}
