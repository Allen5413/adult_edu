package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.eduadmin.student.StudentDao;
import com.allen.dao.fee.feetype.FeeTypeDao;
import com.allen.dao.fee.studentfee.StudentFeeDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.basic.School;
import com.allen.entity.basic.Spec;
import com.allen.entity.eduadmin.Student;
import com.allen.entity.recruit.SignUp;
import com.allen.service.app.signup.FindBmkServiceByIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/15.
 */
@Service
public class FindBmkServiceByIdServiceImpl implements FindBmkServiceByIdService {

    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private SpecDao specDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private FeeTypeDao feeTypeDao;
    @Autowired
    private StudentFeeDao studentFeeDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入报名信息id");
        }
        SignUp signUp = signUpDao.findOne(Long.parseLong(id));
        School school = schoolDao.findOne(signUp.getSchoolId());
        Spec spec = specDao.findOne(signUp.getSpecId());
        Student student = studentDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndTeachPlanIdAndPhone(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getTeachPlanId(), signUp.getPhone());
        json.put("logo", school.getLogo());
        json.put("schoolName", school.getName());
        json.put("specName", spec.getName());
        if(signUp.getState() == SignUp.STATE_CENTER_WAIT || signUp.getState() == SignUp.STATE_CENTER_NOT) {
            json.put("state", 1);
        }
        if(signUp.getState() == SignUp.STATE_SCHOOL_WAIT){
            json.put("state", 2);
        }
        if(signUp.getState() == SignUp.STATE_SCHOOL_PASS){
            json.put("state", 3);
        }
        if(null != student){
            json.put("feeState", student.getFeeState());
            json.put("photoUrl", student.getPhotoUrl());
            json.put("studentName", student.getName());
            json.put("studentCode", student.getCode());
            json.put("studentState", student.getState());
            json.put("fee", feeTypeDao.findTotalFeeBySchoolIdAndTypeIdAndLevelIdAndTpId(student.getSchoolId(), student.getRecruitTypeId(), student.getLevelId(), student.getTeachPlanId()));
            json.put("payFee", studentFeeDao.findTotalFeeByStudentId(student.getId()));
        }
        json.put("status", 1);
        return json;
    }
}
