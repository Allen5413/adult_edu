package com.allen.service.app.studentfee.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.studentfee.FindStudentFeeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.service.app.studentfee.CountFeeNumByRtIdAndYearAndTermService;
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
public class CountFeeNumByRtIdAndYearAndTermServiceImpl implements CountFeeNumByRtIdAndYearAndTermService{

    @Autowired
    private FindStudentFeeDao findStudentFeeDao;
    @Autowired
    private StudentFeeDao studentFeeDao;
    @Autowired
    private FeeTypeDao feeTypeDao;

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
        //查询应缴费，已缴费信息
        BigInteger totalFee = feeTypeDao.countTotalFeeByRtIdAndYearAndTerm(rtId, Integer.parseInt(year), Integer.parseInt(term));
        BigInteger payFee = studentFeeDao.countTotalFeeByRtIdAndYearAndTerm(rtId, Integer.parseInt(year), Integer.parseInt(term));
        BigInteger notPayFee = totalFee.subtract(payFee).doubleValue() < 0 ? new BigInteger("0") : totalFee.subtract(payFee);
        json.put("totalFee", new BigDecimal(totalFee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        json.put("payFee", new BigDecimal(payFee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        json.put("notPayFee", new BigDecimal(notPayFee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        //查询应交学生，已缴费学生登数量
        List<Map> list = findStudentFeeDao.countFeeNumByRtIdAndYearAndTerm(rtId, Integer.parseInt(year), Integer.parseInt(term));
        Map map = list.get(0);
        json.put("feeNum", map.get("feeNum"));
        json.put("payFeeNum", map.get("payFeeNum"));
        json.put("notCleanNum", map.get("notCleanNum"));
        json.put("notPayFeeNum", map.get("notPayFeeNum"));
        json.put("status", 1);
        return json;
    }
}
