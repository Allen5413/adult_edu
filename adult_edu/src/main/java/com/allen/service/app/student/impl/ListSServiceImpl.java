package com.allen.service.app.student.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.service.app.student.ListSService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/8/11.
 */
@Service
public class ListSServiceImpl implements ListSService {

    @Autowired
    private FindStudentDao findStudentDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> params = new HashMap<String, Object>();
        String centerId = request.getParameter("centerId");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("没有传入学习中心id");
        }
        params.put("s.center_id", Long.parseLong(centerId));
        params.put("rt.id", StringUtil.isEmpty(request.getParameter("recruitTypeId")) ? null : Long.parseLong(request.getParameter("recruitTypeId")));
        params.put("s.school_id", StringUtil.isEmpty(request.getParameter("schoolId")) ? null : Long.parseLong(request.getParameter("schoolId")));
        params.put("l.id", StringUtil.isEmpty(request.getParameter("levelId")) ? null : Long.parseLong(request.getParameter("levelId")));
        params.put("sp.id", StringUtil.isEmpty(request.getParameter("specId")) ? null : Long.parseLong(request.getParameter("specId")));
        params.put("s.user_id", StringUtil.isEmpty(request.getParameter("userId")) ? null : Long.parseLong(request.getParameter("userId")));
        params.put("tp.year", StringUtil.isEmpty(request.getParameter("year")) ? null : Integer.parseInt(request.getParameter("year")));
        params.put("tp.term", StringUtil.isEmpty(request.getParameter("term")) ? null : Integer.parseInt(request.getParameter("term")));
        params.put("s.state", StringUtil.isEmpty(request.getParameter("state")) ? null : Integer.parseInt(request.getParameter("state")));
        params.put("s.fee_state", StringUtil.isEmpty(request.getParameter("feeState")) ? null : Integer.parseInt(request.getParameter("feeState")));
        params.put("s.name", StringUtil.isEmpty(request.getParameter("name")) ? null : new Object[]{"%"+request.getParameter("name")+"%", "like"});
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setCountOfCurrentPage(999999999);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", true);
        pageInfo = findStudentDao.findPage(pageInfo, params, sortMap);
        jsonObject.put("num", pageInfo.getTotalCount());
        jsonObject.put("list", pageInfo.getPageResults());
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
