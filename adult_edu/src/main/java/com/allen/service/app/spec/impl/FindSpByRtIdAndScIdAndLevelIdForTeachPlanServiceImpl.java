package com.allen.service.app.spec.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.entity.basic.Spec;
import com.allen.service.app.spec.FindSpByRtIdAndScIdAndLevelIdForTeachPlanService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindSpByRtIdAndScIdAndLevelIdForTeachPlanServiceImpl implements FindSpByRtIdAndScIdAndLevelIdForTeachPlanService {

    @Autowired
    private SpecDao specDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String recruitTypeId = request.getParameter("recruitTypeId");
        String schoolId = request.getParameter("schoolId");
        String levelId = request.getParameter("levelId");
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("没有传入招生类型id");
        }
        if(StringUtil.isEmpty(schoolId)){
            throw new BusinessException("没有传入高校id");
        }
        if(StringUtil.isEmpty(levelId)){
            throw new BusinessException("没有传入层次id");
        }
        List<Spec> list = specDao.findBySchoolIdAndTypeIdAndLevelIdForTeachPlan(Long.parseLong(schoolId), Long.parseLong(recruitTypeId), Long.parseLong(levelId));
        json.put("list", list);
        json.put("status", 1);
        return json;
    }
}
