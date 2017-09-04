package com.allen.service.app.signup.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.School;
import com.allen.entity.basic.Spec;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.recruit.SignUp;
import com.allen.service.app.signup.FindSUByIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/15.
 */
@Service
public class FindSUByIdServiceImpl implements FindSUByIdService {

    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private RecruitTypeDao recruitTypeDao;
    @Autowired
    private LevelDao levelDao;
    @Autowired
    private SpecDao specDao;
    @Autowired
    private TeachPlanDao teachPlanDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入报名信息id");
        }
        SignUp signUp = signUpDao.findOne(Long.parseLong(id));
        if(null != signUp) {
            json.put("id", signUp.getId());
            json.put("name", signUp.getName());
            json.put("sex", signUp.getSex());
            json.put("idCard", signUp.getIdCard());
            json.put("phone", signUp.getPhone());
            json.put("address", signUp.getAddress());
            json.put("qq", signUp.getQq());
            json.put("email", signUp.getEmail());
            json.put("schoolId", signUp.getSchoolId());
            json.put("levelId", signUp.getLevelId());
            json.put("specId", signUp.getSpecId());
            json.put("recruitTypeId", signUp.getRecruitTypeId());
            json.put("teachPlanId", signUp.getTeachPlanId());
            json.put("studyType", signUp.getStudyType());
            json.put("beforeEdu", signUp.getBeforeEdu());
            json.put("beforeGraduationDate", signUp.getBeforeGraduationDate());
            json.put("beforeSchool", signUp.getBeforeSchool());
            json.put("beforeCode", signUp.getBeforeCode());
            json.put("party", signUp.getParty());
            json.put("partyDate", signUp.getPartyDate());
            json.put("reward", signUp.getReward());
            json.put("sourceRemark", signUp.getSourceRemark());
            json.put("resumeDate", signUp.getResumeDate());
            json.put("resumeCompany", signUp.getResumeCompany());
            json.put("resumeAddress", signUp.getResumeAddress());
            json.put("resumePost", signUp.getResumePost());
            json.put("resumeLeaveReason", signUp.getResumeLeaveReason());
            json.put("resumeDate2", signUp.getResumeDate2());
            json.put("resumeCompany2", signUp.getResumeCompany2());
            json.put("resumeAddress2", signUp.getResumeAddress2());
            json.put("resumePost2", signUp.getResumePost2());
            json.put("resumeLeaveReason2", signUp.getResumeLeaveReason2());
            json.put("resumeDate3", signUp.getResumeDate3());
            json.put("resumeCompany3", signUp.getResumeCompany3());
            json.put("resumeAddress3", signUp.getResumeAddress3());
            json.put("resumePost3", signUp.getResumePost3());
            json.put("resumeLeaveReason3", signUp.getResumeLeaveReason3());
            json.put("familyName", signUp.getFamilyName());
            json.put("familyRelation", signUp.getFamilyPolitical());
            json.put("familyAge", signUp.getFamilyAge());
            json.put("familyPost", signUp.getFamilyPost());
            json.put("familyCompany", signUp.getFamilyCompany());
            json.put("familyName2", signUp.getFamilyName2());
            json.put("familyRelation2", signUp.getFamilyPolitical2());
            json.put("familyAge2", signUp.getFamilyAge2());
            json.put("familyPost2", signUp.getFamilyPost2());
            json.put("familyCompany2", signUp.getFamilyCompany2());
            json.put("familyName3", signUp.getFamilyName3());
            json.put("familyRelation3", signUp.getFamilyPolitical3());
            json.put("familyAge3", signUp.getFamilyAge3());
            json.put("familyPost3", signUp.getFamilyPost3());
            json.put("familyCompany3", signUp.getFamilyCompany3());
            json.put("photoUrl", signUp.getPhotoUrl());
            json.put("idCardFrontUrl", signUp.getIdCardFrontUrl());
            json.put("idCardBackUrl", signUp.getIdCardBackUrl());
            json.put("diplomaUrl", signUp.getDiplomaUrl());
            json.put("xxwUrl", signUp.getXxwUrl());
            json.put("ydsUrl", signUp.getYdsUrl());

            School school = schoolDao.findOne(signUp.getSchoolId());
            RecruitType recruitType = recruitTypeDao.findOne(signUp.getRecruitTypeId());
            Level level = levelDao.findOne(signUp.getLevelId());
            Spec spec = specDao.findOne(signUp.getSpecId());
            TeachPlan teachPlan = teachPlanDao.findOne(signUp.getTeachPlanId());
            json.put("schoolName", null == school ? "" : school.getName());
            json.put("recruitTypeName", null == recruitType ? "" : recruitType.getName());
            json.put("levelName", null == level ? "" : level.getName());
            json.put("specName", null == spec ? "" : spec.getName());
            json.put("teachPlanName", null == teachPlan ? "" : teachPlan.getYear()+"年"+(TeachPlan.TERM_SPRING == teachPlan.getTerm() ? "春季":"秋季"));
        }
        json.put("status", 1);
        return json;
    }
}
