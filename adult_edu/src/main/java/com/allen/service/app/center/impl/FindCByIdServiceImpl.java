package com.allen.service.app.center.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.user.center.CenterDao;
import com.allen.entity.user.Center;
import com.allen.service.app.center.FindCByIdService;
import com.allen.service.user.user.FindUserByCenterIdForCenterManService;
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
public class FindCByIdServiceImpl implements FindCByIdService {

    @Autowired
    private FindUserByCenterIdForCenterManService findUserByCenterIdForCenterManService;
    @Autowired
    private CenterDao centerDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入学习中心id");
        }
        Center center = centerDao.findOne(Long.parseLong(id));
        //查询联系人
        List<Map> list = findUserByCenterIdForCenterManService.find(Long.parseLong(id));
        json.put("name", center.getName());
        json.put("code", center.getCode());
        json.put("address", center.getAddress());
        json.put("email", center.getEmail());
        json.put("remark", center.getRemark());
        json.put("logo", center.getLogo());
        json.put("list", list);
        return json;
    }
}
