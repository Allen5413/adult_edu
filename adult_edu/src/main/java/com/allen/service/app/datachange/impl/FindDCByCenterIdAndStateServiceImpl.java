package com.allen.service.app.datachange.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.datachange.FindDataChangeDao;
import com.allen.service.app.datachange.FindDCByCenterIdAndStateService;
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
public class FindDCByCenterIdAndStateServiceImpl implements FindDCByCenterIdAndStateService {

    @Autowired
    private FindDataChangeDao findDataChangeDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String centerId = request.getParameter("centerId");
        String state = request.getParameter("state");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("没有传入学习中心id");
        }
        if(StringUtil.isEmpty(state)){
            throw new BusinessException("没有传入状态");
        }
        List<Map> list = findDataChangeDao.findByCenterIdAndState(Long.parseLong(centerId), Integer.parseInt(state));
        json.put("dataChangeList", list);
        json.put("status", 1);
        return json;
    }
}
