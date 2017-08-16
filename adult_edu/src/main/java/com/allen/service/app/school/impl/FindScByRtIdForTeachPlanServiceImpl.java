package com.allen.service.app.school.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.app.school.FindScByRtIdForTeachPlanService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindScByRtIdForTeachPlanServiceImpl implements FindScByRtIdForTeachPlanService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String recruitTypeId = request.getParameter("recruitTypeId");
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("没有传入招生类型id");
        }
        List<School> list = schoolDao.findByTypeIdForTeachPlan(Long.parseLong(recruitTypeId));
        json.put("list", list);
        json.put("status", 1);
        return json;
    }
}
