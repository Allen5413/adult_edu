package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.service.app.signup.CountPeopleNumByRtIdAndYearAndTermService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/10.
 */
@Service
public class CountPeopleNumByRtIdAndYearAndTermServiceImpl implements CountPeopleNumByRtIdAndYearAndTermService {

    @Autowired
    private FindStudentDao findStudentDao;


    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String recruitTypeId = request.getParameter("recruitTypeId");
        String year = request.getParameter("year");
        String term = request.getParameter("term");
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("没有传入招生类型id");
        }
        if(StringUtil.isEmpty(year)){
            throw new BusinessException("没有传入招生批次年");
        }
        if(StringUtil.isEmpty(term)){
            throw new BusinessException("没有传入招生批次季");
        }
        long rtId = Long.parseLong(recruitTypeId);
        //查询共计招生人数,分销商人数，本部人数
        List<Map> list = findStudentDao.countByRtIdAndYearAndTerm(rtId, Integer.parseInt(year), Integer.parseInt(term));
        Map map = list.get(0);
        json.put("totalNum", null == map.get("totalNum") ? 0 : map.get("totalNum"));
        json.put("fxsNum", null == map.get("fxsNum") ? 0 : map.get("fxsNum"));
        json.put("centerNum", null == map.get("centerNum") ? 0 : map.get("centerNum"));
        //查询各分销商招生人数
        List<Map> list2 = findStudentDao.countForFXSByRtIdAndYearAndTerm(rtId, Integer.parseInt(year), Integer.parseInt(term));
        json.put("list", list2);
        json.put("status", 1);
        return json;
    }
}
