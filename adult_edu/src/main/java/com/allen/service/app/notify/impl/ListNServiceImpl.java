package com.allen.service.app.notify.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.notify.FindNotifyDao;
import com.allen.service.app.notify.ListNService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/9/13.
 */
@Service
public class ListNServiceImpl implements ListNService {

    @Autowired
    private FindNotifyDao findNotifyDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> params = new HashMap<String, String>();
        String centerId = request.getParameter("centerId");
        String sendObject = request.getParameter("sendObject");
        String studentId = request.getParameter("studentId");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("没有传入学习中心id");
        }
        if(!StringUtil.isEmpty(sendObject) && "0".equals(sendObject) && StringUtil.isEmpty(studentId)){
            throw new BusinessException("没有传入学生id");
        }
        params.put("centerId", centerId);
        params.put("type", request.getParameter("type"));
        params.put("sendObject", sendObject);
        params.put("studentId", studentId);
        params.put("stateFlag", request.getParameter("state"));
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(StringUtil.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum")));
        pageInfo.setCountOfCurrentPage(StringUtil.isEmpty(request.getParameter("pageSize")) ? 10 : Integer.parseInt(request.getParameter("pageSize")));
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("s.id", true);
        pageInfo = findNotifyDao.findPage(pageInfo, params);
        jsonObject.put("num", pageInfo.getTotalCount());
        jsonObject.put("list", pageInfo.getPageResults());
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
