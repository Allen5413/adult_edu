package com.allen.service.app.recruittype.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.service.app.recruittype.FindRTByCenterIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/8/10.
 */
@Service
public class FindRTByCenterIdServiceImpl implements FindRTByCenterIdService {

    @Autowired
    private RecruitTypeDao recruitTypeDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String centerId = request.getParameter("centerId");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("没有传入学习中心id");
        }
        List<RecruitType> list = recruitTypeDao.findByCenterId(Long.parseLong(centerId));
        json.put("list", list);
        json.put("status", 1);
        return json;
    }
}
