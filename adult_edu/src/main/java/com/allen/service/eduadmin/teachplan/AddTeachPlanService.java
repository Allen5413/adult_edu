package com.allen.service.eduadmin.teachplan;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.eduadmin.TeachPlan;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
public interface AddTeachPlanService {
    public JSONObject add(HttpServletRequest request, TeachPlan teachPlan)throws Exception;
}
