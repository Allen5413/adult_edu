package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.recruit.SignUp;
import com.allen.entity.user.User;
import com.allen.service.app.signup.EditSUService;
import com.allen.service.recruit.signup.EditSignUpService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/15.
 */
@Service
public class EditSUServiceImpl implements EditSUService {

    @Autowired
    private EditSignUpService editSignUpService;

    @Override
    public JSONObject edit(HttpServletRequest request) throws Exception {
        SignUp signUp = new SignUp();
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String recruitTypeId = request.getParameter("recruitTypeId");
        String schoolId = request.getParameter("schoolId");
        String levelId = request.getParameter("levelId");
        String specId = request.getParameter("specId");
        String teachPlanId = request.getParameter("teachPlanId");
        String sourceRemark = request.getParameter("sourceRemark");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("报名信息id不能为空");
        }
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
        signUp.setId(Long.parseLong(id));
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
        signUp.setBeforeEdu(request.getParameter("beforeEdu"));
        signUp.setBeforeGraduationDate(request.getParameter("beforeGraduationDate"));
        signUp.setBeforeSchool(request.getParameter("beforeSchool"));
        signUp.setParty(request.getParameter("party"));
        signUp.setPartyDate(request.getParameter("partyDate"));
        signUp.setReward(request.getParameter("reward"));
        signUp.setResumeDate(request.getParameter("resumeDate"));
        signUp.setResumeCompany(request.getParameter("resumeCompany"));
        signUp.setResumeAddress(request.getParameter("resumeAddress"));
        signUp.setResumePost(request.getParameter("resumePost"));
        signUp.setResumeLeaveReason(request.getParameter("resumeLeaveReason"));
        signUp.setResumeDate2(request.getParameter("resumeDate2"));
        signUp.setResumeCompany2(request.getParameter("resumeCompany2"));
        signUp.setResumeAddress2(request.getParameter("resumeAddress2"));
        signUp.setResumePost2(request.getParameter("resumePost2"));
        signUp.setResumeLeaveReason2(request.getParameter("resumeLeaveReason2"));
        signUp.setResumeDate3(request.getParameter("resumeDate3"));
        signUp.setResumeCompany3(request.getParameter("resumeCompany3"));
        signUp.setResumeAddress3(request.getParameter("resumeAddress3"));
        signUp.setResumePost3(request.getParameter("resumePost3"));
        signUp.setResumeLeaveReason3(request.getParameter("resumeLeaveReason3"));
        signUp.setFamilyName(request.getParameter("familyName"));
        signUp.setFamilyRelation(request.getParameter("familyRelation"));
        signUp.setFamilyAge(request.getParameter("familyAge"));
        signUp.setFamilyPost(request.getParameter("familyPost"));
        signUp.setFamilyCompany(request.getParameter("familyCompany"));
        signUp.setFamilyName2(request.getParameter("familyName2"));
        signUp.setFamilyRelation2(request.getParameter("familyRelation2"));
        signUp.setFamilyAge2(request.getParameter("familyAge2"));
        signUp.setFamilyPost2(request.getParameter("familyPost2"));
        signUp.setFamilyCompany2(request.getParameter("familyCompany2"));
        signUp.setFamilyName3(request.getParameter("familyName3"));
        signUp.setFamilyRelation3(request.getParameter("familyRelation3"));
        signUp.setFamilyAge3(request.getParameter("familyAge3"));
        signUp.setFamilyPost3(request.getParameter("familyPost3"));
        signUp.setFamilyCompany3(request.getParameter("familyCompany3"));
        signUp.setPhotoUrl(request.getParameter("photoUrl"));
        signUp.setIdCardFrontUrl(request.getParameter("idCardFrontUrl"));
        signUp.setIdCardBackUrl(request.getParameter("idCardBackUrl"));
        signUp.setDiplomaUrl(request.getParameter("diplomaUrl"));
        signUp.setXxwUrl(request.getParameter("xxwUrl"));
        signUp.setYdsUrl(request.getParameter("ydsUrl"));

        editSignUpService.edit(request, signUp, -1, User.ISOPERATEAUDIT_NOT, -1, "", 0);
        json.put("status", 1);
        return json;
    }
}
