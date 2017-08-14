package com.allen.service.app.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.user.user.FindUserDao;
import com.allen.service.app.user.ListUByWhereService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/8/14.
 */
@Service
public class ListUByWhereServiceImpl implements ListUByWhereService {

    @Autowired
    private FindUserDao findUserDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Map<String, String> params = new HashMap<String, String>();
        String centerId = request.getParameter("centerId");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("没有传入学习中心id");
        }
        params.put("centerId", centerId);
        params.put("name", request.getParameter("name"));
        params.put("type", request.getParameter("type"));
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setCountOfCurrentPage(999999999);
        pageInfo = findUserDao.findPage(pageInfo, params, "u.name");
        jsonObject.put("list", pageInfo.getPageResults());
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
