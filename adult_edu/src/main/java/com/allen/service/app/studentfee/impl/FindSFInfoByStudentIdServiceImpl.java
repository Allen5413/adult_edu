package com.allen.service.app.studentfee.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.entity.eduadmin.Student;
import com.allen.service.app.studentfee.FindSFInfoByStudentIdService;
import com.allen.service.fee.studentfee.FindStudentFeeByStudentIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/16.
 */
@Service
public class FindSFInfoByStudentIdServiceImpl implements FindSFInfoByStudentIdService {

    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private StudentFeeDao studentFeeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private FindStudentFeeByStudentIdService findStudentFeeByStudentIdService;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入学生id");
        }
        Student student = studentDao.findOne(Long.parseLong(id));
        json.put("fee", feeTypeDao.findTotalFeeBySchoolIdAndTypeIdAndLevelIdAndTpId(student.getSchoolId(), student.getRecruitTypeId(), student.getLevelId(), student.getTeachPlanId()));
        json.put("payFee", studentFeeDao.findTotalFeeByStudentId(student.getId()));
        json.put("feeList", findStudentFeeByStudentIdService.find(student.getId()));
        json.put("status", 1);
        return json;
    }
}
