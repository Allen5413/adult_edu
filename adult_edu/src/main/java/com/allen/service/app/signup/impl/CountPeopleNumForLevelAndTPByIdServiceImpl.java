package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.recruit.signup.FindSignUpDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.service.app.signup.CountPeopleNumForLevelAndTPByIdService;
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
public class CountPeopleNumForLevelAndTPByIdServiceImpl implements CountPeopleNumForLevelAndTPByIdService {

    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private FindSignUpDao findSignUpDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String recruitTypeId = request.getParameter("recruitTypeId");
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("没有传入招生类型id");
        }
        long rtId = Long.parseLong(recruitTypeId);
        //查询招生类型下一共招生人数
        BigInteger totalNum = signUpDao.countByRtId(rtId);
        //查询各层次招生人数统计
        List<Map> levelList = findSignUpDao.countForLevelByRtId(rtId);
        //查询各批次招生人数统计
        List<Map> teachPlanList = findSignUpDao.countForTPByRtId(rtId);

        json.put("totalNum", totalNum);
        json.put("levelList", levelList);
        json.put("teachPlanList", teachPlanList);
        json.put("status", 1);
        return json;
    }
}
