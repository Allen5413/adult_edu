package com.allen.service.app.feetype.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.FindStudentDao;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.feetype.FindFeeTypeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.service.app.feetype.CountFeeByRtIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/10.
 */
@Service
public class CountFeeByRtIdServiceImpl implements CountFeeByRtIdService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private FindStudentDao findStudentDao;
    @Autowired
    private FindFeeTypeDao findFeeTypeDao;
    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private StudentFeeDao studentFeeDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String recruitTypeId = request.getParameter("recruitTypeId");
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("没有传入招生类型id");
        }
        long rtId = Long.parseLong(recruitTypeId);
        //查询招生类型下一共要交好多钱
        BigInteger totalFee = feeTypeDao.countTotalFeeByRtId(rtId);
        //查询已交学费金额
        BigInteger payFee = studentFeeDao.countTotalFeeByRtId(rtId);
        //查询未交学生人数和未缴完学费的人数
        List<Map> list = findStudentDao.countFeeStateNumByRtId(rtId);
        Map map = list.get(0);
        //查询各批次应交费用集合
        List<Map> tpList = findFeeTypeDao.countTotalFeeForYearAndTermByRtId(rtId);

        json.put("totalFee", new BigDecimal(totalFee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.put("payFee", new BigDecimal(payFee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.put("notFeeNum", map.get("notFeeNum"));
        json.put("notCleanNum", map.get("notCleanNum"));
        json.put("teachPlanList", tpList);
        json.put("status", 1);
        return json;
    }
}
