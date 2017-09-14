package com.allen.service.app.teachplan.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.service.app.teachplan.FindTPByRtIdAndScIdAndLevelIdAndSpIdForTeachPlanService;
import com.allen.service.app.teachplan.FindTPByScIdAndSpIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindTPByScIdAndSpIdServiceImpl implements FindTPByScIdAndSpIdService {

    @Autowired
    private TeachPlanDao teachPlanDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String schoolId = request.getParameter("schoolId");
        String specId = request.getParameter("specId");
        if(StringUtil.isEmpty(schoolId)){
            throw new BusinessException("没有传入高校id");
        }
        if(StringUtil.isEmpty(specId)){
            throw new BusinessException("没有传入专业id");
        }
        List<TeachPlan> list = teachPlanDao.findBySchoolIdAndSpecIdOrderByYearDescTermDesc(Long.parseLong(schoolId), Long.parseLong(specId));
        json.put("list", list);
        json.put("status", 1);
        return json;
    }
}
