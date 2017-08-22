package com.allen.service.app.datachange.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.user.User;
import com.allen.service.app.datachange.FindDCByIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Allen on 2017/8/14.
 */
@Service
public class FindDCByIdServiceImpl implements FindDCByIdService {

    @Autowired
    private DataChangeDao dataChangeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入id");
        }
        DataChange dataChange = dataChangeDao.findOne(Long.parseLong(id));
        User user = userDao.findOne(dataChange.getCenterId());
        json.put("id", dataChange.getId());
        json.put("userName", null == user ? "" : user.getName());
        json.put("phone", null == user ? "" : user.getPhone());
        json.put("createTime", dataChange.getCreateTime());
        json.put("editReson", dataChange.getEditReson());
        json.put("changeContent", dataChange.getChangeContent());
        json.put("state", dataChange.getState());
        json.put("refuseContent", dataChange.getRefuseContent());
        json.put("noticeTitle", "");
        json.put("noticeType", "");
        json.put("noticeSendee", "");
        json.put("noticeContent", "");
        json.put("status", 1);
        return json;
    }
}
