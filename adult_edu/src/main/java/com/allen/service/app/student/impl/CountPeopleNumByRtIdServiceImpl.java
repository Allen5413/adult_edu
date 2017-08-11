package com.allen.service.app.student.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.service.app.student.CountPeopleNumByRtIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/11.
 */
@Service
public class CountPeopleNumByRtIdServiceImpl implements CountPeopleNumByRtIdService {

    @Autowired
    private FindStudentDao findStudentDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String recruitTypeId = request.getParameter("recruitTypeId");
        Long userId = StringUtil.isEmpty(request.getParameter("userId")) ? null : Long.parseLong(request.getParameter("userId"));
        String term = request.getParameter("term");
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("没有传入招生类型id");
        }
        long rtId = Long.parseLong(recruitTypeId);
        //查询各个状态的学生人数
        List<Map> list = findStudentDao.countNumForStateByRtId(rtId, userId);
        Map map = list.get(0);
        json.put("zdNum", map.get("zdNum"));
        json.put("xxNum", map.get("xxNum"));
        json.put("txNum", map.get("txNum"));
        json.put("byNum", map.get("byNum"));
        //查询各分批次的在读学生人数
        List<Map> list2 = findStudentDao.countTPNumForStateNORMALByRtId(rtId, userId);
        json.put("list", list2);
        json.put("status", 1);
        return json;
    }
}
