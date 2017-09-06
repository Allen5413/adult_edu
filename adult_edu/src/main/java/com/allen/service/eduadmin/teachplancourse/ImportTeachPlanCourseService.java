package com.allen.service.eduadmin.teachplancourse;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.eduadmin.TeachPlan;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
public interface ImportTeachPlanCourseService {
    public JSONObject importCourse(HttpServletRequest request, long id)throws Exception;
}
