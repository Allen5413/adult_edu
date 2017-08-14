package com.allen.service.app.index.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.dao.user.user.UserDao;
import com.allen.service.app.index.AppIndexService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/14.
 */
@Service
public class AppIndexServiceImpl implements AppIndexService {

    @Autowired
    private FindStudentDao findStudentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private SpecDao specDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String centerId = request.getParameter("centerId");
        String userId = request.getParameter("userId");
        if(StringUtil.isEmpty(centerId)){
            throw new BusinessException("没有传入学习中心id");
        }
        //查询学生各个状态的人数
        List<Map> list = findStudentDao.countNumForStateWhereByCenterIdAndUserId(Long.parseLong(centerId), StringUtil.isEmpty(userId) ? null : Long.parseLong(userId));
        Map map = list.get(0);
        json.put("totalNum", map.get("totalNum"));
        json.put("zdNum", map.get("zdNum"));
        json.put("notPayNum", map.get("notPayNum"));
        json.put("notCleanNum", map.get("notCleanNum"));
        //查询分销商数量
        BigInteger fxsNum = userDao.countNumForFxsByCenterId(Long.parseLong(centerId));
        //查询高校数量
        BigInteger schoolNum = schoolDao.countNumByCenterId(Long.parseLong(centerId));
        //查询专业数量
        List<String> list2 = specDao.findForNameByCenterId(Long.parseLong(centerId));
        json.put("fxsNum", fxsNum);
        json.put("schoolNum", schoolNum);
        json.put("specNum", null == list2 ? 0 : list2.size());
        json.put("status", 1);
        return json;
    }
}
