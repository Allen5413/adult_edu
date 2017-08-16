package com.allen.service.app.level.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.entity.basic.Level;
import com.allen.service.app.level.FindLByRtIdAndScIdForTeachPlanService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindLByRtIdAndScIdForTeachPlanServiceImpl implements FindLByRtIdAndScIdForTeachPlanService {

    @Autowired
    private LevelDao levelDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String recruitTypeId = request.getParameter("recruitTypeId");
        String schoolId = request.getParameter("schoolId");
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("没有传入招生类型id");
        }
        if(StringUtil.isEmpty(schoolId)){
            throw new BusinessException("没有传入高校id");
        }
        List<Level> list = levelDao.findBySchoolIdAndTypeIdForTeachPlan(Long.parseLong(schoolId), Long.parseLong(recruitTypeId));
        json.put("list", list);
        json.put("status", 1);
        return json;
    }
}
