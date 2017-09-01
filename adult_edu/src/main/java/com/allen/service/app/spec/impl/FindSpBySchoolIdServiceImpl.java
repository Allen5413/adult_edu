package com.allen.service.app.spec.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.entity.basic.Spec;
import com.allen.service.app.spec.FindSpBySchoolIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindSpBySchoolIdServiceImpl implements FindSpBySchoolIdService {

    @Autowired
    private SpecDao specDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String schoolId = request.getParameter("schoolId");
        if(StringUtil.isEmpty(schoolId)){
            throw new BusinessException("没有传入高校id");
        }
        List<Spec> list = specDao.findBySchoolIdOrderByCode(Long.parseLong(schoolId));
        json.put("list", list);
        json.put("status", 1);
        return json;
    }
}
