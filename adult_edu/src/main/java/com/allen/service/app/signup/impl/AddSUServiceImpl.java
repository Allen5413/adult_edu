package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.recruit.SignUp;
import com.allen.service.app.signup.AddSUService;
import com.allen.service.recruit.signup.AddSignUpService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/15.
 */
@Service
public class AddSUServiceImpl implements AddSUService {

    @Autowired
    private AddSignUpService addSignUpService;

    @Override
    public JSONObject add(HttpServletRequest request) throws Exception {
        SignUp signUp = new SignUp();
        JSONObject json = new JSONObject();
        String name = StringUtil.getDecode(request, "name");
        String sex = request.getParameter("sex");
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String address = StringUtil.getDecode(request, "address");
        String recruitTypeId = request.getParameter("recruitTypeId");
        String schoolId = request.getParameter("schoolId");
        String levelId = request.getParameter("levelId");
        String specId = request.getParameter("specId");
        String teachPlanId = request.getParameter("teachPlanId");
        String sourceRemark = StringUtil.getDecode(request, "sourceRemark");
        if(StringUtil.isEmpty(name)){
            throw new BusinessException("姓名不能为空");
        }
        if(StringUtil.isEmpty(sex)){
            throw new BusinessException("性别不能为空");
        }
        if(StringUtil.isEmpty(idCard)){
            throw new BusinessException("身份证不能为空");
        }
        if(StringUtil.isEmpty(phone)){
            throw new BusinessException("手机号码不能为空");
        }
        if(StringUtil.isEmpty(address)){
            throw new BusinessException("家庭住址不能为空");
        }
        if(StringUtil.isEmpty(recruitTypeId)){
            throw new BusinessException("招生类型不能为空");
        }
        if(StringUtil.isEmpty(schoolId)){
            throw new BusinessException("学校不能为空");
        }
        if(StringUtil.isEmpty(levelId)){
            throw new BusinessException("层次不能为空");
        }
        if(StringUtil.isEmpty(specId)){
            throw new BusinessException("专业不能为空");
        }
        if(StringUtil.isEmpty(teachPlanId)){
            throw new BusinessException("招生批次不能为空");
        }
        if(StringUtil.isEmpty(sourceRemark)){
            throw new BusinessException("生源备注不能为空");
        }
        signUp.setName(name);
        signUp.setSex(Integer.parseInt(sex));
        signUp.setIdCard(idCard);
        signUp.setPhone(phone);
        signUp.setAddress(address);
        signUp.setRecruitTypeId(Long.parseLong(recruitTypeId));
        signUp.setSchoolId(Long.parseLong(schoolId));
        signUp.setLevelId(Long.parseLong(levelId));
        signUp.setSpecId(Long.parseLong(specId));
        signUp.setTeachPlanId(Long.parseLong(teachPlanId));
        signUp.setSourceRemark(sourceRemark);
        signUp.setQq(request.getParameter("qq"));
        signUp.setEmail(request.getParameter("email"));
        signUp.setStudyType(null == request.getParameter("studyType") ? null : Integer.parseInt(request.getParameter("studyType")));
        signUp.setBeforeEdu(StringUtil.getDecode(request, "beforeEdu"));
        signUp.setBeforeGraduationDate(StringUtil.getDecode(request, "beforeGraduationDate"));
        signUp.setBeforeSchool(StringUtil.getDecode(request, "beforeSchool"));
        signUp.setParty(StringUtil.getDecode(request, "party"));
        signUp.setPartyDate(StringUtil.getDecode(request, "partyDate"));
        signUp.setReward(StringUtil.getDecode(request, "reward"));
        signUp.setResumeDate(StringUtil.getDecode(request, "resumeDate"));
        signUp.setResumeCompany(StringUtil.getDecode(request, "resumeCompany"));
        signUp.setResumeAddress(StringUtil.getDecode(request, "resumeAddress"));
        signUp.setResumePost(StringUtil.getDecode(request, "resumePost"));
        signUp.setResumeLeaveReason(StringUtil.getDecode(request, "resumeLeaveReason"));
        signUp.setResumeDate2(StringUtil.getDecode(request, "resumeDate2"));
        signUp.setResumeCompany2(StringUtil.getDecode(request, "resumeCompany2"));
        signUp.setResumeAddress2(StringUtil.getDecode(request, "resumeAddress2"));
        signUp.setResumePost2(StringUtil.getDecode(request, "resumePost2"));
        signUp.setResumeLeaveReason2(StringUtil.getDecode(request, "resumeLeaveReason2"));
        signUp.setResumeDate3(StringUtil.getDecode(request, "resumeDate3"));
        signUp.setResumeCompany3(StringUtil.getDecode(request, "resumeCompany3"));
        signUp.setResumeAddress3(StringUtil.getDecode(request, "resumeAddress3"));
        signUp.setResumePost3(StringUtil.getDecode(request, "resumePost3"));
        signUp.setResumeLeaveReason3(StringUtil.getDecode(request, "resumeLeaveReason3"));
        signUp.setFamilyName(StringUtil.getDecode(request, "familyName"));
        signUp.setFamilyRelation(StringUtil.getDecode(request, "familyRelation"));
        signUp.setFamilyAge(StringUtil.getDecode(request, "familyAge"));
        signUp.setFamilyPost(StringUtil.getDecode(request, "familyPost"));
        signUp.setFamilyCompany(StringUtil.getDecode(request, "familyCompany"));
        signUp.setFamilyName2(StringUtil.getDecode(request, "familyName2"));
        signUp.setFamilyRelation2(StringUtil.getDecode(request, "familyRelation2"));
        signUp.setFamilyAge2(StringUtil.getDecode(request, "familyAge2"));
        signUp.setFamilyPost2(StringUtil.getDecode(request, "familyPost2"));
        signUp.setFamilyCompany2(StringUtil.getDecode(request, "familyCompany2"));
        signUp.setFamilyName3(StringUtil.getDecode(request, "familyName3"));
        signUp.setFamilyRelation3(StringUtil.getDecode(request, "familyRelation3"));
        signUp.setFamilyAge3(StringUtil.getDecode(request, "familyAge3"));
        signUp.setFamilyPost3(StringUtil.getDecode(request, "familyPost3"));
        signUp.setFamilyCompany3(StringUtil.getDecode(request, "familyCompany3"));
        signUp.setPhotoUrl(request.getParameter("photoUrl"));
        signUp.setIdCardFrontUrl(request.getParameter("idCardFrontUrl"));
        signUp.setIdCardBackUrl(request.getParameter("idCardBackUrl"));
        signUp.setDiplomaUrl(request.getParameter("diplomaUrl"));
        signUp.setXxwUrl(request.getParameter("xxwUrl"));
        signUp.setYdsUrl(request.getParameter("ydsUrl"));

        addSignUpService.add(request, signUp);
        json.put("status", 1);
        return json;
    }
}
