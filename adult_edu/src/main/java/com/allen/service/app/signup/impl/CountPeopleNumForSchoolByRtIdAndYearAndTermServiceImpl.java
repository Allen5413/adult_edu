package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.recruit.signup.FindSignUpDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.service.app.signup.CountPeopleNumForSchoolByRtIdAndYearAndTermService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/10.
 */
@Service
public class CountPeopleNumForSchoolByRtIdAndYearAndTermServiceImpl implements CountPeopleNumForSchoolByRtIdAndYearAndTermService {

    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private FindSignUpDao findSignUpDao;

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
        //查询招生类型下一共招生人数
        BigInteger totalNum = signUpDao.countByRtId(rtId);
        //查询各层次招生人数统计
        List<Map> levelList = findSignUpDao.countForLevelByRtIdAndYearAndTerm(rtId, Integer.parseInt(year), Integer.parseInt(term));
        //查询各高校招生人数统计
        List<Map> schoolList = findSignUpDao.countForSchoolByRtIdAndYearAndTerm(rtId, Integer.parseInt(year), Integer.parseInt(term));

        json.put("totalNum", totalNum);
        json.put("levelList", levelList);
        json.put("schoolList", schoolList);
        json.put("status", 1);
        return json;
    }
}
