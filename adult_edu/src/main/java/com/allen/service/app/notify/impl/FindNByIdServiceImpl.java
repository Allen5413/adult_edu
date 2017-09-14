package com.allen.service.app.notify.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.notify.NotifyDao;
import com.allen.entity.notify.Notify;
import com.allen.service.app.notify.FindNByIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Allen on 2017/9/13.
 */
@Service
public class FindNByIdServiceImpl implements FindNByIdService {

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入通知信息id");
        }
        Notify notify = notifyDao.findOne(Long.parseLong(id));
        if(null != notify) {
            json.put("id", notify.getId());
            json.put("title", notify.getTitle());
            json.put("type", notify.getType());
            json.put("operator", notify.getOperator());
            json.put("operateTime", notify.getOperateTime());
            json.put("objectRemark", notify.getObjectRemark());
        }
        json.put("status", 1);
        return json;
    }
}
