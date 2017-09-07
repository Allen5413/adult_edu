package com.allen.service.app.studentfee.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.fee.FeeType;
import com.allen.entity.fee.StudentFee;
import com.allen.entity.user.User;
import com.allen.service.app.studentfee.FindSFDetailByStudentIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindSFDetailByStudentIdServiceImpl implements FindSFDetailByStudentIdService {

    @Autowired
    private StudentFeeDao studentFeeDao;
    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入学生缴费信息id");
        }
        StudentFee studentFee = studentFeeDao.findOne(Long.parseLong(id));
        FeeType feeType = feeTypeDao.findOne(studentFee.getFeeTypeId());
        Student student = studentDao.findOne(studentFee.getStudentId());
        json.put("date", studentFee.getOperateTime());
        json.put("type", feeType.getName());
        json.put("feeStyle", studentFee.getFeeStyle());
        json.put("payFee", new BigDecimal(studentFee.getFee()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
        json.put("fee", new BigDecimal(feeType.getFee()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP));
        if(studentFee.getFeeStyle() == StudentFee.FEE_STYLE_FXS) {
            User user = userDao.findOne(student.getUserId());
            json.put("userName", user.getName());
        }
        json.put("status", 1);
        return json;
    }
}
