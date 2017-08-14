package com.allen.service.app.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.app.user.FindFxsByIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/14.
 */
@Service
public class FindFxsByIdServiceImpl implements FindFxsByIdService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FindStudentDao findStudentDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入经销商id");
        }
        User user = userDao.findOne(Long.parseLong(id));
        //查询该分销商下的各个批次的招生人数，学生各个状态的人数集合
        List<Map> list = findStudentDao.countTPNumForStateWhereByUserId(Long.parseLong(id));
        json.put("name", user.getName());
        json.put("linkMan", user.getLinkMan());
        json.put("phone", user.getPhone());
        json.put("code", user.getCode());
        json.put("address", user.getAddress());
        json.put("createTime", user.getCreateTime());
        json.put("teachPlanList", list);
        return json;
    }
}
