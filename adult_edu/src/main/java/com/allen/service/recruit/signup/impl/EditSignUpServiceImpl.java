package com.allen.service.recruit.signup.impl;

import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.basic.level.LevelDao;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.spec.SpecDao;
import com.allen.dao.datachange.DataChangeDao;
import com.allen.dao.eduadmin.recruittype.RecruitTypeDao;
import com.allen.dao.eduadmin.teachplan.TeachPlanDao;
import com.allen.dao.recruit.signup.SignUpDao;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.basic.Level;
import com.allen.entity.basic.School;
import com.allen.entity.basic.Spec;
import com.allen.entity.datachange.DataChange;
import com.allen.entity.eduadmin.RecruitType;
import com.allen.entity.eduadmin.TeachPlan;
import com.allen.entity.recruit.SignUp;
import com.allen.entity.user.User;
import com.allen.service.recruit.signup.EditSignUpService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@Service
public class EditSignUpServiceImpl implements EditSignUpService {

    @Autowired
    private SignUpDao signUpDao;
    @Autowired
    private ConfigProp configProp;
    @Autowired
    private DataChangeDao dataChangeDao;
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
    @Autowired
    private UserDao userDao;

    @Override
    public void edit(HttpServletRequest request, SignUp signUp, long centerId, int isAudit, long operateId, String editReson, int isTimeOut) throws Exception {
        SignUp signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndIdCard(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getIdCard());
        if(null != signUp2 && signUp2.getId() != signUp.getId()){
            throw new BusinessException("身份证号码在同一个学校、招生类型、层次、专业下已存在！");
        }
        signUp2 = signUpDao.findByCenterIdAndSchoolIdAndRecruitTypeIdAndLevelIdAndSpecIdAndPhone(signUp.getCenterId(), signUp.getSchoolId(), signUp.getRecruitTypeId(), signUp.getLevelId(), signUp.getSpecId(), signUp.getPhone());
        if(null != signUp2 && signUp2.getId() != signUp.getId()){
            throw new BusinessException("手机号码在同一个学校、招生类型、层次、专业下已存在！");
        }
        //查询操作是否需要审核
        if(isAudit == User.ISOPERATEAUDIT_NOT || isTimeOut == 0) {
            if (null == signUp2) {
                signUp2 = signUpDao.findOne(signUp.getId());
            }
            String photoUrl = signUp.getPhotoUrl();
            String idCardFrontUrl = signUp.getIdCardFrontUrl();
            String idCardBackUrl = signUp.getIdCardBackUrl();
            String diplomaUrl = signUp.getDiplomaUrl();
            String xxwUrl = signUp.getXxwUrl();
            String ydsUrl = signUp.getYdsUrl();
            if(!photoUrl.equals(signUp2.getPhotoUrl())) {
                if (StringUtil.isEmpty(photoUrl)) {
                    throw new BusinessException("请上传照片");
                }else{
                    //把上传的照片从临时目录剪切到正式目录，并把文件名改成id
                    UpLoadFileUtil.custFile(request, photoUrl, configProp.getSignUp().get("photoUrl"), signUp.getId() + ".png");
                }
            }
            if(!idCardFrontUrl.equals(signUp2.getIdCardFrontUrl())) {
                if (StringUtil.isEmpty(idCardFrontUrl)) {
                    throw new BusinessException("请上传身份证正面照");
                }else{
                    UpLoadFileUtil.custFile(request, idCardFrontUrl, configProp.getSignUp().get("idCardFrontUrl"), signUp.getId()+".png");
                }
            }
            if(!idCardBackUrl.equals(signUp2.getIdCardBackUrl())) {
                if (StringUtil.isEmpty(idCardBackUrl)) {
                    throw new BusinessException("请上传身份证背面照");
                }else{
                    UpLoadFileUtil.custFile(request, idCardBackUrl, configProp.getSignUp().get("idCardBackUrl"), signUp.getId()+".png");
                }
            }
            if(!diplomaUrl.equals(signUp2.getDiplomaUrl())) {
                if (StringUtil.isEmpty(diplomaUrl)) {
                    throw new BusinessException("请上传学历证书照");
                }else{
                    UpLoadFileUtil.custFile(request, diplomaUrl, configProp.getSignUp().get("diplomaUrl"), signUp.getId()+".png");
                }
            }
            if(!xxwUrl.equals(signUp2.getXxwUrl())){
                if (StringUtil.isEmpty(xxwUrl)) {
                    signUp.setXxwUrl("");
                }else{
                    UpLoadFileUtil.custFile(request, xxwUrl, configProp.getSignUp().get("xxwUrl"), signUp.getId()+".png");
                    signUp.setXxwUrl(configProp.getSignUp().get("xxwUrl") + signUp.getId() + ".png");
                }
            }
            if(!ydsUrl.equals(signUp2.getYdsUrl())){
                if (StringUtil.isEmpty(ydsUrl)) {
                    signUp.setYdsUrl("");
                }else{
                    UpLoadFileUtil.custFile(request, ydsUrl, configProp.getSignUp().get("ydsUrl"), signUp.getId()+".png");
                    signUp.setYdsUrl(configProp.getSignUp().get("ydsUrl") + signUp.getId() + ".png");
                }
            }

            signUp.setCenterId(signUp2.getCenterId());
            signUp.setCerator(signUp2.getCerator());
            signUp.setCreateTime(signUp2.getCreateTime());
            signUp.setReason(signUp2.getReason());

            signUpDao.save(signUp);
        }else{
            signUp2 = signUpDao.findOne(signUp.getId());
            String changeContent = signUp2.getName()+"的报名信息  ";
            String changeTableField = "";
            if(signUp.getState() != signUp2.getState()) {
                if (signUp.getState() == SignUp.STATE_CENTER_WAIT){
                    changeContent += "状态变更为<span style='color:red'>待审核</span>  ";
                    changeTableField += "state=" + SignUp.STATE_CENTER_WAIT + ",";
                }
                if (signUp.getState() == SignUp.STATE_CENTER_NOT){
                    changeContent += "状态变更为<span style='color:red'>中心未通过</span>  ";
                    changeTableField += "state=" + SignUp.STATE_CENTER_NOT + ",";
                }
                if (signUp.getState() == SignUp.STATE_SCHOOL_WAIT){
                    changeContent += "状态变更为<span style='color:red'>高校审核中</span>  ";
                    changeTableField += "state=" + SignUp.STATE_SCHOOL_WAIT + ",";
                }
                if (signUp.getState() == SignUp.STATE_SCHOOL_NOT){
                    changeContent += "状态变更为<span style='color:red'>高校未通过</span>  ";
                    changeTableField += "state=" + SignUp.STATE_SCHOOL_NOT + ",";
                }
                if (signUp.getState() == SignUp.STATE_SCHOOL_PASS){
                    changeContent += "状态变更为<span style='color:red'>高校已通过</span>  ";
                    changeTableField += "state=" + SignUp.STATE_SCHOOL_PASS + ",";
                }
            }
            if(!signUp.getReason().equals(signUp2.getReason())){
                changeContent += "拒绝原因变更为<span style='color:red'>"+signUp.getReason()+"</span>  ";
                changeTableField += "reason='"+signUp.getReason()+"',";
            }
            if(signUp.getUserId() != signUp2.getUserId()){
                User user = userDao.findOne(signUp.getUserId());
                changeContent += "分销商变更为<span style='color:red'>"+user.getName()+"</span>  ";
                changeTableField += "user_id="+signUp.getUserId() +",";
            }
            if(signUp.getSchoolId() != signUp2.getSchoolId()){
                School school = schoolDao.findOne(signUp2.getSchoolId());
                School school2 = schoolDao.findOne(signUp.getSchoolId());
                changeContent += "高校<span style='color:red'>"+school.getName()+"</span>变更为<span style='color:red'>"+school2.getName()+"</span>  ";
                changeTableField += "school_id="+signUp.getSchoolId() +",";
            }
            if(signUp.getRecruitTypeId() != signUp2.getRecruitTypeId()){
                RecruitType recruitType = recruitTypeDao.findOne(signUp2.getRecruitTypeId());
                RecruitType recruitType2 = recruitTypeDao.findOne(signUp.getRecruitTypeId());
                changeContent += "招生类型<span style='color:red'>"+recruitType.getName()+"</span>变更为<span style='color:red'>"+recruitType2.getName()+"</span>  ";
                changeTableField += "recruit_type_id="+signUp.getRecruitTypeId() +",";
            }
            if(signUp.getLevelId() != signUp2.getLevelId()){
                Level level = levelDao.findOne(signUp2.getLevelId());
                Level level2 = levelDao.findOne(signUp.getLevelId());
                changeContent += "层次<span style='color:red'>"+level.getName()+"</span>变更为<span style='color:red'>"+level2.getName()+"</span>  ";
                changeTableField += "level_id="+signUp.getLevelId() +",";
            }
            if(signUp.getSpecId() != signUp2.getSpecId()){
                Spec spec = specDao.findOne(signUp2.getSpecId());
                Spec spec2 = specDao.findOne(signUp.getSpecId());
                changeContent += "专业<span style='color:red'>"+spec.getName()+"</span>变更为<span style='color:red'>"+spec2.getName()+"</span>  ";
                changeTableField += "spec_id="+signUp.getSpecId() +",";
            }
            if(signUp.getTeachPlanId() != signUp2.getTeachPlanId()){
                TeachPlan teachPlan = teachPlanDao.findOne(signUp2.getTeachPlanId());
                TeachPlan teachPlan2 = teachPlanDao.findOne(signUp.getTeachPlanId());
                changeContent += "批次<span style='color:red'>"+teachPlan.getYear()+"年"+(teachPlan.getTerm() == TeachPlan.TERM_SPRING ? "春季":"秋季")+"</span>变更为<span style='color:red'>"+teachPlan2.getYear()+"年"+(teachPlan2.getTerm() == TeachPlan.TERM_SPRING ? "春季":"秋季")+"</span>  ";
                changeTableField += "teach_plan_id="+signUp.getTeachPlanId() +",";
            }
            if(!signUp.getName().equals(signUp2.getName())){
                changeContent += "姓名<span style='color:red'>"+signUp2.getName()+"</span>变更为<span style='color:red'>"+signUp.getName()+"</span>  ";
                changeTableField += "name='"+signUp.getName() +"',";
            }
            if(signUp.getSex() != signUp2.getSex()){
                changeContent += "性别<span style='color:red'>"+(signUp2.getSex() == 0 ? "男":"女")+"</span>变更为<span style='color:red'>"+(signUp.getSex() == 0 ? "男":"女")+"</span>  ";
                changeTableField += "sex="+signUp.getSex() +",";
            }
            if(!signUp.getIdCard().equals(signUp2.getIdCard())){
                changeContent += "身份证号<span style='color:red'>"+signUp2.getIdCard()+"</span>变更为<span style='color:red'>"+signUp.getIdCard()+"</span>  ";
                changeTableField += "id_card='"+signUp.getIdCard() +"',";
            }
            if(!signUp.getPhone().equals(signUp2.getPhone())){
                changeContent += "手机号码<span style='color:red'>"+signUp2.getPhone()+"</span>变更为<span style='color:red'>"+signUp.getPhone()+"</span>  ";
                changeTableField += "phone='"+signUp.getPhone() +"',";
            }
            if(!signUp.getQq().equals(signUp2.getQq())){
                changeContent += "qq<span style='color:red'>"+signUp2.getQq()+"</span>变更为<span style='color:red'>"+signUp.getQq()+"</span>  ";
                changeTableField += "qq='"+signUp.getQq() +"',";
            }
            if(signUp.getStudyType() != signUp2.getStudyType()){
                if(signUp.getStudyType() == 0) {
                    changeContent += "学习方式变更为<span style='color:red'>脱产</span>  ";
                    changeTableField += "study_type=" + signUp.getStudyType() + ",";
                }
                if(signUp.getStudyType() == 1) {
                    changeContent += "学习方式变更为<span style='color:red'>业余</span>  ";
                    changeTableField += "study_type=" + signUp.getStudyType() + ",";
                }
                if(signUp.getStudyType() == 2) {
                    changeContent += "学习方式变更为<span style='color:red'>函授</span>  ";
                    changeTableField += "study_type=" + signUp.getStudyType() + ",";
                }
                if(signUp.getStudyType() == 3) {
                    changeContent += "学习方式变更为<span style='color:red'>远程教育</span>  ";
                    changeTableField += "study_type=" + signUp.getStudyType() + ",";
                }
            }
            if(!signUp.getAddress().equals(signUp2.getAddress())){
                changeContent += "家庭住址<span style='color:red'>"+signUp2.getAddress()+"</span>变更为<span style='color:red'>"+signUp.getAddress()+"</span>  ";
                changeTableField += "address='"+signUp.getAddress() +"',";
            }
            if(!signUp.getZipCode().equals(signUp2.getZipCode())){
                changeContent += "邮政编码<span style='color:red'>"+signUp2.getZipCode()+"</span>变更为<span style='color:red'>"+signUp.getZipCode()+"</span>  ";
                changeTableField += "zip_code='"+signUp.getZipCode() +"',";
            }
            if(!signUp.getSourceRemark().equals(signUp2.getSourceRemark())){
                changeContent += "生源备注<span style='color:red'>"+signUp2.getSourceRemark()+"</span>变更为<span style='color:red'>"+signUp.getSourceRemark()+"</span>  ";
                changeTableField += "source_remark='"+signUp.getSourceRemark() +"',";
            }
            if(!signUp.getBeforeEdu().equals(signUp2.getBeforeEdu())){
                changeContent += "入学前的学历<span style='color:red'>"+signUp2.getBeforeEdu()+"</span>变更为<span style='color:red'>"+signUp.getBeforeEdu()+"</span>  ";
                changeTableField += "before_edu='"+signUp.getBeforeEdu() + "',";
            }
            if(!signUp.getBeforeGraduationDate().equals(signUp2.getBeforeGraduationDate())){
                changeContent += "入学前的毕业时间<span style='color:red'>"+signUp2.getBeforeGraduationDate()+"</span>变更为<span style='color:red'>"+signUp.getBeforeGraduationDate()+"</span>  ";
                changeTableField += "before_graduation_date='"+signUp.getBeforeGraduationDate() + "',";
            }
            if(!signUp.getBeforeSchool().equals(signUp2.getBeforeSchool())){
                changeContent += "入学前的学校<span style='color:red'>"+signUp2.getBeforeSchool()+"</span>变更为<span style='color:red'>"+signUp.getBeforeSchool()+"</span>  ";
                changeTableField += "before_school='"+signUp.getBeforeSchool() + "',";
            }
            if(!signUp.getBeforeCode().equals(signUp2.getBeforeCode())){
                changeContent += "入学前的证书编号<span style='color:red'>"+signUp2.getBeforeCode()+"</span>变更为<span style='color:red'>"+signUp.getBeforeCode()+"</span>  ";
                changeTableField += "before_code='"+signUp.getBeforeCode() + "',";
            }
            if(!signUp.getParty().equals(signUp2.getParty())){
                changeContent += "是否党团员<span style='color:red'>"+signUp2.getParty()+"</span>变更为<span style='color:red'>"+signUp.getParty()+"</span>  ";
                changeTableField += "party='"+signUp.getParty() + "',";
            }
            if(!signUp.getPartyDate().equals(signUp2.getPartyDate())){
                changeContent += "入党团年月<span style='color:red'>"+signUp2.getPartyDate()+"</span>变更为<span style='color:red'>"+signUp.getPartyDate()+"</span>  ";
                changeTableField += "party_date='"+signUp.getPartyDate() + "',";
            }
            if(!signUp.getResumeDate().equals(signUp2.getResumeDate())){
                changeContent += "简历1的起止时间<span style='color:red'>"+signUp2.getResumeDate()+"</span>变更为<span style='color:red'>"+signUp.getResumeDate()+"</span>  ";
                changeTableField += "resume_date='"+signUp.getResumeDate() + "',";
            }
            if(!signUp.getResumeCompany().equals(signUp2.getResumeCompany())){
                changeContent += "简历1的工作单位<span style='color:red'>"+signUp2.getResumeCompany()+"</span>变更为<span style='color:red'>"+signUp.getResumeCompany()+"</span>  ";
                changeTableField += "resume_company='"+signUp.getResumeCompany() + "',";
            }
            if(!signUp.getResumeAddress().equals(signUp2.getResumeAddress())){
                changeContent += "简历1的工作地址<span style='color:red'>"+signUp2.getResumeAddress()+"</span>变更为<span style='color:red'>"+signUp.getResumeAddress()+"</span>  ";
                changeTableField += "resume_address='"+signUp.getResumeAddress() + "',";
            }
            if(!signUp.getResumePost().equals(signUp2.getResumePost())){
                changeContent += "简历1的职务<span style='color:red'>"+signUp2.getResumePost()+"</span>变更为<span style='color:red'>"+signUp.getResumePost()+"</span>  ";
                changeTableField += "resume_post='"+signUp.getResumePost() + "',";
            }
            if(!signUp.getResumeLeaveReason().equals(signUp2.getResumeLeaveReason())){
                changeContent += "简历1的离职原因<span style='color:red'>"+signUp2.getResumeLeaveReason()+"</span>变更为<span style='color:red'>"+signUp.getResumeLeaveReason()+"</span>  ";
                changeTableField += "resume_leave_reason='"+signUp.getResumeLeaveReason() + "',";
            }
            if(!signUp.getResumeDate2().equals(signUp2.getResumeDate2())){
                changeContent += "简历2的起止时间<span style='color:red'>"+signUp2.getResumeDate2()+"</span>变更为<span style='color:red'>"+signUp.getResumeDate2()+"</span>  ";
                changeTableField += "resume_date2='"+signUp.getResumeDate2() + "',";
            }
            if(!signUp.getResumeCompany2().equals(signUp2.getResumeCompany2())){
                changeContent += "简历2的工作单位<span style='color:red'>"+signUp2.getResumeCompany2()+"</span>变更为<span style='color:red'>"+signUp.getResumeCompany2()+"</span>  ";
                changeTableField += "resume_company2='"+signUp.getResumeCompany2() + "',";
            }
            if(!signUp.getResumeAddress2().equals(signUp2.getResumeAddress2())){
                changeContent += "简历2的工作地址<span style='color:red'>"+signUp2.getResumeAddress2()+"</span>变更为<span style='color:red'>"+signUp.getResumeAddress2()+"</span>  ";
                changeTableField += "resume_address2='"+signUp.getResumeAddress2() + "',";
            }
            if(!signUp.getResumePost2().equals(signUp2.getResumePost2())){
                changeContent += "简历2的职务<span style='color:red'>"+signUp2.getResumePost2()+"</span>变更为<span style='color:red'>"+signUp.getResumePost2()+"</span>  ";
                changeTableField += "resume_post2='"+signUp.getResumePost2() + "',";
            }
            if(!signUp.getResumeLeaveReason2().equals(signUp2.getResumeLeaveReason2())){
                changeContent += "简历2的离职原因<span style='color:red'>"+signUp2.getResumeLeaveReason2()+"</span>变更为<span style='color:red'>"+signUp.getResumeLeaveReason2()+"</span>  ";
                changeTableField += "resume_leave_reason2='"+signUp.getResumeLeaveReason2() + "',";
            }
            if(!signUp.getResumeDate3().equals(signUp2.getResumeDate3())){
                changeContent += "简历3的起止时间<span style='color:red'>"+signUp2.getResumeDate3()+"</span>变更为<span style='color:red'>"+signUp.getResumeDate3()+"</span>  ";
                changeTableField += "resume_date3='"+signUp.getResumeDate3() + "',";
            }
            if(!signUp.getResumeCompany3().equals(signUp2.getResumeCompany3())){
                changeContent += "简历3的工作单位<span style='color:red'>"+signUp2.getResumeCompany3()+"</span>变更为<span style='color:red'>"+signUp.getResumeCompany3()+"</span>  ";
                changeTableField += "resume_company3='"+signUp.getResumeCompany3() + "',";
            }
            if(!signUp.getResumeAddress3().equals(signUp2.getResumeAddress3())){
                changeContent += "简历3的工作地址<span style='color:red'>"+signUp2.getResumeAddress3()+"</span>变更为<span style='color:red'>"+signUp.getResumeAddress3()+"</span>  ";
                changeTableField += "resume_address3='"+signUp.getResumeAddress3() + "',";
            }
            if(!signUp.getResumePost3().equals(signUp2.getResumePost3())){
                changeContent += "简历3的职务<span style='color:red'>"+signUp2.getResumePost3()+"</span>变更为<span style='color:red'>"+signUp.getResumePost3()+"</span>  ";
                changeTableField += "resume_post3='"+signUp.getResumePost3() + "',";
            }
            if(!signUp.getResumeLeaveReason3().equals(signUp2.getResumeLeaveReason3())){
                changeContent += "简历3的离职原因<span style='color:red'>"+signUp2.getResumeLeaveReason3()+"</span>变更为<span style='color:red'>"+signUp.getResumeLeaveReason3()+"</span>  ";
                changeTableField += "resume_leave_reason3='"+signUp.getResumeLeaveReason3() + "',";
            }
            if(!signUp.getFamilyName().equals(signUp2.getFamilyName())){
                changeContent += "家庭成员1的姓名<span style='color:red'>"+signUp2.getFamilyName()+"</span>变更为<span style='color:red'>"+signUp.getFamilyName()+"</span>  ";
                changeTableField += "family_name='"+signUp.getFamilyName() + "',";
            }
            if(!signUp.getFamilyRelation().equals(signUp2.getFamilyRelation())){
                changeContent += "家庭成员1的关系、称谓<span style='color:red'>"+signUp2.getFamilyRelation()+"</span>变更为<span style='color:red'>"+signUp.getFamilyRelation()+"</span>  ";
                changeTableField += "family_relation='"+signUp.getFamilyRelation() + "',";
            }
            if(!signUp.getFamilyAge().equals(signUp2.getFamilyAge())){
                changeContent += "家庭成员1的年龄<span style='color:red'>"+signUp2.getFamilyAge()+"</span>变更为<span style='color:red'>"+signUp.getFamilyAge()+"</span>  ";
                changeTableField += "family_age='"+signUp.getFamilyAge() + "',";
            }
            if(!signUp.getFamilyPolitical().equals(signUp2.getFamilyPolitical())){
                changeContent += "家庭成员1的政治面貌<span style='color:red'>"+signUp2.getFamilyPolitical()+"</span>变更为<span style='color:red'>"+signUp.getFamilyPolitical()+"</span>  ";
                changeTableField += "family_political='"+signUp.getFamilyPolitical() + "',";
            }
            if(!signUp.getFamilyCompany().equals(signUp2.getFamilyCompany())){
                changeContent += "家庭成员1的工作单位<span style='color:red'>"+signUp2.getFamilyCompany()+"</span>变更为<span style='color:red'>"+signUp.getFamilyCompany()+"</span>  ";
                changeTableField += "family_company='"+signUp.getFamilyCompany() + "',";
            }
            if(!signUp.getFamilyPost().equals(signUp2.getFamilyPost())){
                changeContent += "家庭成员1的职务<span style='color:red'>"+signUp2.getFamilyPost()+"</span>变更为<span style='color:red'>"+signUp.getFamilyPost()+"</span>  ";
                changeTableField += "family_post='"+signUp.getFamilyPost() + "',";
            }
            if(!signUp.getFamilyName2().equals(signUp2.getFamilyName2())){
                changeContent += "家庭成员2的姓名<span style='color:red'>"+signUp2.getFamilyName2()+"</span>变更为<span style='color:red'>"+signUp.getFamilyName2()+"</span>  ";
                changeTableField += "family_name2='"+signUp.getFamilyName2() + "',";
            }
            if(!signUp.getFamilyRelation2().equals(signUp2.getFamilyRelation2())){
                changeContent += "家庭成员2的关系、称谓<span style='color:red'>"+signUp2.getFamilyRelation2()+"</span>变更为<span style='color:red'>"+signUp.getFamilyRelation2()+"</span>  ";
                changeTableField += "family_relation2='"+signUp.getFamilyRelation2() + "',";
            }
            if(!signUp.getFamilyAge2().equals(signUp2.getFamilyAge2())){
                changeContent += "家庭成员2的年龄<span style='color:red'>"+signUp2.getFamilyAge2()+"</span>变更为<span style='color:red'>"+signUp.getFamilyAge2()+"</span>  ";
                changeTableField += "family_age2='"+signUp.getFamilyAge2() + "',";
            }
            if(!signUp.getFamilyPolitical2().equals(signUp2.getFamilyPolitical2())){
                changeContent += "家庭成员2的政治面貌<span style='color:red'>"+signUp2.getFamilyPolitical2()+"</span>变更为<span style='color:red'>"+signUp.getFamilyPolitical2()+"</span>  ";
                changeTableField += "family_political2='"+signUp.getFamilyPolitical2() + "',";
            }
            if(!signUp.getFamilyCompany2().equals(signUp2.getFamilyCompany2())){
                changeContent += "家庭成员2的工作单位<span style='color:red'>"+signUp2.getFamilyCompany2()+"</span>变更为<span style='color:red'>"+signUp.getFamilyCompany2()+"</span>  ";
                changeTableField += "family_company2='"+signUp.getFamilyCompany2() + "',";
            }
            if(!signUp.getFamilyPost2().equals(signUp2.getFamilyPost2())){
                changeContent += "家庭成员2的职务<span style='color:red'>"+signUp2.getFamilyPost2()+"</span>变更为<span style='color:red'>"+signUp.getFamilyPost2()+"</span>  ";
                changeTableField += "family_post2='"+signUp.getFamilyPost2() + "',";
            }
            if(!signUp.getFamilyName3().equals(signUp2.getFamilyName3())){
                changeContent += "家庭成员3的姓名<span style='color:red'>"+signUp2.getFamilyName3()+"</span>变更为<span style='color:red'>"+signUp.getFamilyName3()+"</span>  ";
                changeTableField += "family_name3='"+signUp.getFamilyName3() + "',";
            }
            if(!signUp.getFamilyRelation3().equals(signUp2.getFamilyRelation3())){
                changeContent += "家庭成员3的关系、称谓<span style='color:red'>"+signUp2.getFamilyRelation3()+"</span>变更为<span style='color:red'>"+signUp.getFamilyRelation3()+"</span>  ";
                changeTableField += "family_relation3='"+signUp.getFamilyRelation3() + "',";
            }
            if(!signUp.getFamilyAge3().equals(signUp2.getFamilyAge3())){
                changeContent += "家庭成员3的年龄<span style='color:red'>"+signUp2.getFamilyAge3()+"</span>变更为<span style='color:red'>"+signUp.getFamilyAge3()+"</span>  ";
                changeTableField += "family_age3='"+signUp.getFamilyAge3() + "',";
            }
            if(!signUp.getFamilyPolitical3().equals(signUp2.getFamilyPolitical3())){
                changeContent += "家庭成员3的政治面貌<span style='color:red'>"+signUp2.getFamilyPolitical3()+"</span>变更为<span style='color:red'>"+signUp.getFamilyPolitical3()+"</span>  ";
                changeTableField += "family_political3='"+signUp.getFamilyPolitical3() + "',";
            }
            if(!signUp.getFamilyCompany3().equals(signUp2.getFamilyCompany3())){
                changeContent += "家庭成员3的工作单位<span style='color:red'>"+signUp2.getFamilyCompany3()+"</span>变更为<span style='color:red'>"+signUp.getFamilyCompany3()+"</span>  ";
                changeTableField += "family_company3='"+signUp.getFamilyCompany3() + "',";
            }
            if(!signUp.getFamilyPost3().equals(signUp2.getFamilyPost3())){
                changeContent += "家庭成员3的职务<span style='color:red'>"+signUp2.getFamilyPost3()+"</span>变更为<span style='color:red'>"+signUp.getFamilyPost3()+"</span>  ";
                changeTableField += "family_post3='"+signUp.getFamilyPost3() + "',";
            }
            if(!signUp.getReward().equals(signUp2.getReward())){
                changeContent += "获得奖励<span style='color:red'>"+signUp2.getReward()+"</span>变更为<span style='color:red'>"+signUp.getReward()+"</span>  ";
                changeTableField += "reward='"+signUp.getReward() + "',";
            }
            if(!signUp.getPhotoUrl().equals(signUp2.getPhotoUrl())){
                changeTableField += "photo_url='"+signUp.getPhotoUrl() + "@#@photo_url',";
            }
            if(!signUp.getIdCardFrontUrl().equals(signUp2.getIdCardFrontUrl())){
                changeTableField += "id_card_front_url='"+signUp.getIdCardFrontUrl() + "@#@id_card_front_url',";
            }
            if(!signUp.getIdCardBackUrl().equals(signUp2.getIdCardBackUrl())){
                changeTableField += "id_card_back_url='"+signUp.getIdCardBackUrl() + "@#@id_card_back_url',";
            }
            if(!signUp.getDiplomaUrl().equals(signUp2.getDiplomaUrl())){
                changeTableField += "diploma_url='"+signUp.getDiplomaUrl() + "@#@diploma_url',";
            }
            if(!signUp.getXxwUrl().equals(signUp2.getXxwUrl())){
                changeTableField += "xxw_url='"+signUp.getXxwUrl() + "@#@xxw_url',";
            }
            if(!signUp.getYdsUrl().equals(signUp2.getYdsUrl())){
                changeTableField += "yds_url='"+signUp.getYdsUrl() + "@#@yds_url',";
            }

            DataChange dataChange = new DataChange();
            dataChange.setCenterId(centerId);
            dataChange.setChangeContent(changeContent);
            dataChange.setState(DataChange.STATE_AUDIT_WAIT);
            dataChange.setType(DataChange.TYPE_EDIT);
            dataChange.setChangeTable("sign_up");
            dataChange.setStudentName(signUp.getName());
            dataChange.setChangeTableId(signUp.getId());
            dataChange.setChangeTableField(changeTableField);
            dataChange.setCreatorId(operateId);
            dataChange.setEditReson(editReson);
            dataChangeDao.save(dataChange);
        }
    }
}
