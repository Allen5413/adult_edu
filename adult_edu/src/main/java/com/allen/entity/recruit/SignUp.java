package com.allen.entity.recruit;

import javax.persistence.*;
import java.util.Date;

/**
 * 报名信息表
 * Created by Allen on 2017/6/29.
 */
@Entity
@Table(name = "sign_up")
public class SignUp {

    public static final int STATE_CENTER_WAIT = 0;     //待审核
    public static final int STATE_CENTER_NOT = 1;      //中心未通过
    public static final int STATE_SCHOOL_WAIT = 2;     //学校审核中
    public static final int STATE_SCHOOL_NOT = 3;      //学校未通过
    public static final int STATE_SCHOOL_PASS = 4;     //学校已通过

    @Id
    @GeneratedValue
    private long id;
    private long centerId;
    private int state;
    private String reason;
    private long schoolId;
    private long recruitTypeId;
    private long levelId;
    private long specId;
    private long teachPlanId;
    private String name;
    private int sex;                            //性别[0：男；1：女]
    private String idCard;
    private String phone;
    private String qq;
    private Integer studyType;                  //学习方式[0：脱产、1：业余、2：函授、3：远程教育]
    private String address;
    private String zipCode;
    private String sourceRemark;            //生源备注
    private String beforeEdu;               //入学前的学历
    private String beforeGraduationDate;    //入学前的毕业时间
    private String beforeSchool;            //入学前的学校
    private String beforeCode;              //入学前证书编号
    private String party;                   //是否党团员
    private String partyDate;               //入党团年月
    private String resumeDate;              //简历1的起止时间
    private String resumeCompany;           //简历1的工作单位
    private String resumeAddress;           //简历1的工作地址
    private String resumePost;              //简历1的职务
    private String resumeLeaveReason;       //简历1的离职原因
    private String resumeDate2;              //简历2的起止时间
    private String resumeCompany2;           //简历2的工作单位
    private String resumeAddress2;           //简历2的工作地址
    private String resumePost2;              //简历2的职务
    private String resumeLeaveReason2;       //简历2的离职原因
    private String resumeDate3;              //简历3的起止时间
    private String resumeCompany3;           //简历3的工作单位
    private String resumeAddress3;           //简历3的工作地址
    private String resumePost3;              //简历3的职务
    private String resumeLeaveReason3;       //简历3的离职原因
    private String familyName;              //家庭成员姓名
    private String familyRelation;          //家庭成员关系，称谓
    private String familyAge;               //家庭成员年龄
    private String familyPolitical;         //家庭成员政治面貌
    private String familyCompany;           //家庭成员工作单位
    private String familyPost;              //家庭成员职务
    private String familyName2;              //家庭成员2姓名
    private String familyRelation2;          //家庭成员2关系，称谓
    private String familyAge2;               //家庭成员2年龄
    private String familyPolitical2;         //家庭成员2政治面貌
    private String familyCompany2;           //家庭成员2工作单位
    private String familyPost2;              //家庭成员2职务
    private String familyName3;              //家庭成员3姓名
    private String familyRelation3;          //家庭成员3关系，称谓
    private String familyAge3;               //家庭成员3年龄
    private String familyPolitical3;         //家庭成员3政治面貌
    private String familyCompany3;           //家庭成员3工作单位
    private String familyPost3;              //家庭成员3职务
    private String reward;                   //何时何地获得奖励
    private String photoUrl;                 //上传照片地址
    private String idCardFrontUrl;           //身份证正面照片地址
    private String idCardBackUrl;            //身份证反面照片地址
    private String diplomaUrl;               //学历证书照片地址
    private String xxwUrl;                   //学信网认证照片url
    private String ydsUrl;                   //异地生证明
    private String cerator;
    private Date createTime = new Date();       //创建时间
    private String operator;                    //操作人
    private Date operateTime = new Date();      //操作时间

    @Transient
    private String stateStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public long getRecruitTypeId() {
        return recruitTypeId;
    }

    public void setRecruitTypeId(long recruitTypeId) {
        this.recruitTypeId = recruitTypeId;
    }

    public long getLevelId() {
        return levelId;
    }

    public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

    public long getSpecId() {
        return specId;
    }

    public void setSpecId(long specId) {
        this.specId = specId;
    }

    public long getTeachPlanId() {
        return teachPlanId;
    }

    public void setTeachPlanId(long teachPlanId) {
        this.teachPlanId = teachPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getStudyType() {
        return studyType;
    }

    public void setStudyType(Integer studyType) {
        this.studyType = studyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getSourceRemark() {
        return sourceRemark;
    }

    public void setSourceRemark(String sourceRemark) {
        this.sourceRemark = sourceRemark;
    }

    public String getBeforeEdu() {
        return beforeEdu;
    }

    public void setBeforeEdu(String beforeEdu) {
        this.beforeEdu = beforeEdu;
    }

    public String getBeforeGraduationDate() {
        return beforeGraduationDate;
    }

    public void setBeforeGraduationDate(String beforeGraduationDate) {
        this.beforeGraduationDate = beforeGraduationDate;
    }

    public String getBeforeSchool() {
        return beforeSchool;
    }

    public void setBeforeSchool(String beforeSchool) {
        this.beforeSchool = beforeSchool;
    }

    public String getBeforeCode() {
        return beforeCode;
    }

    public void setBeforeCode(String beforeCode) {
        this.beforeCode = beforeCode;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPartyDate() {
        return partyDate;
    }

    public void setPartyDate(String partyDate) {
        this.partyDate = partyDate;
    }

    public String getResumeDate() {
        return resumeDate;
    }

    public void setResumeDate(String resumeDate) {
        this.resumeDate = resumeDate;
    }

    public String getResumeCompany() {
        return resumeCompany;
    }

    public void setResumeCompany(String resumeCompany) {
        this.resumeCompany = resumeCompany;
    }

    public String getResumeAddress() {
        return resumeAddress;
    }

    public void setResumeAddress(String resumeAddress) {
        this.resumeAddress = resumeAddress;
    }

    public String getResumePost() {
        return resumePost;
    }

    public void setResumePost(String resumePost) {
        this.resumePost = resumePost;
    }

    public String getResumeLeaveReason() {
        return resumeLeaveReason;
    }

    public void setResumeLeaveReason(String resumeLeaveReason) {
        this.resumeLeaveReason = resumeLeaveReason;
    }

    public String getResumeDate2() {
        return resumeDate2;
    }

    public void setResumeDate2(String resumeDate2) {
        this.resumeDate2 = resumeDate2;
    }

    public String getResumeCompany2() {
        return resumeCompany2;
    }

    public void setResumeCompany2(String resumeCompany2) {
        this.resumeCompany2 = resumeCompany2;
    }

    public String getResumeAddress2() {
        return resumeAddress2;
    }

    public void setResumeAddress2(String resumeAddress2) {
        this.resumeAddress2 = resumeAddress2;
    }

    public String getResumePost2() {
        return resumePost2;
    }

    public void setResumePost2(String resumePost2) {
        this.resumePost2 = resumePost2;
    }

    public String getResumeLeaveReason2() {
        return resumeLeaveReason2;
    }

    public void setResumeLeaveReason2(String resumeLeaveReason2) {
        this.resumeLeaveReason2 = resumeLeaveReason2;
    }

    public String getResumeDate3() {
        return resumeDate3;
    }

    public void setResumeDate3(String resumeDate3) {
        this.resumeDate3 = resumeDate3;
    }

    public String getResumeCompany3() {
        return resumeCompany3;
    }

    public void setResumeCompany3(String resumeCompany3) {
        this.resumeCompany3 = resumeCompany3;
    }

    public String getResumeAddress3() {
        return resumeAddress3;
    }

    public void setResumeAddress3(String resumeAddress3) {
        this.resumeAddress3 = resumeAddress3;
    }

    public String getResumePost3() {
        return resumePost3;
    }

    public void setResumePost3(String resumePost3) {
        this.resumePost3 = resumePost3;
    }

    public String getResumeLeaveReason3() {
        return resumeLeaveReason3;
    }

    public void setResumeLeaveReason3(String resumeLeaveReason3) {
        this.resumeLeaveReason3 = resumeLeaveReason3;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getFamilyAge() {
        return familyAge;
    }

    public void setFamilyAge(String familyAge) {
        this.familyAge = familyAge;
    }

    public String getFamilyPolitical() {
        return familyPolitical;
    }

    public void setFamilyPolitical(String familyPolitical) {
        this.familyPolitical = familyPolitical;
    }

    public String getFamilyCompany() {
        return familyCompany;
    }

    public void setFamilyCompany(String familyCompany) {
        this.familyCompany = familyCompany;
    }

    public String getFamilyPost() {
        return familyPost;
    }

    public void setFamilyPost(String familyPost) {
        this.familyPost = familyPost;
    }

    public String getFamilyName2() {
        return familyName2;
    }

    public void setFamilyName2(String familyName2) {
        this.familyName2 = familyName2;
    }

    public String getFamilyRelation2() {
        return familyRelation2;
    }

    public void setFamilyRelation2(String familyRelation2) {
        this.familyRelation2 = familyRelation2;
    }

    public String getFamilyAge2() {
        return familyAge2;
    }

    public void setFamilyAge2(String familyAge2) {
        this.familyAge2 = familyAge2;
    }

    public String getFamilyPolitical2() {
        return familyPolitical2;
    }

    public void setFamilyPolitical2(String familyPolitical2) {
        this.familyPolitical2 = familyPolitical2;
    }

    public String getFamilyCompany2() {
        return familyCompany2;
    }

    public void setFamilyCompany2(String familyCompany2) {
        this.familyCompany2 = familyCompany2;
    }

    public String getFamilyPost2() {
        return familyPost2;
    }

    public void setFamilyPost2(String familyPost2) {
        this.familyPost2 = familyPost2;
    }

    public String getFamilyName3() {
        return familyName3;
    }

    public void setFamilyName3(String familyName3) {
        this.familyName3 = familyName3;
    }

    public String getFamilyRelation3() {
        return familyRelation3;
    }

    public void setFamilyRelation3(String familyRelation3) {
        this.familyRelation3 = familyRelation3;
    }

    public String getFamilyAge3() {
        return familyAge3;
    }

    public void setFamilyAge3(String familyAge3) {
        this.familyAge3 = familyAge3;
    }

    public String getFamilyPolitical3() {
        return familyPolitical3;
    }

    public void setFamilyPolitical3(String familyPolitical3) {
        this.familyPolitical3 = familyPolitical3;
    }

    public String getFamilyCompany3() {
        return familyCompany3;
    }

    public void setFamilyCompany3(String familyCompany3) {
        this.familyCompany3 = familyCompany3;
    }

    public String getFamilyPost3() {
        return familyPost3;
    }

    public void setFamilyPost3(String familyPost3) {
        this.familyPost3 = familyPost3;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getIdCardFrontUrl() {
        return idCardFrontUrl;
    }

    public void setIdCardFrontUrl(String idCardFrontUrl) {
        this.idCardFrontUrl = idCardFrontUrl;
    }

    public String getIdCardBackUrl() {
        return idCardBackUrl;
    }

    public void setIdCardBackUrl(String idCardBackUrl) {
        this.idCardBackUrl = idCardBackUrl;
    }

    public String getDiplomaUrl() {
        return diplomaUrl;
    }

    public void setDiplomaUrl(String diplomaUrl) {
        this.diplomaUrl = diplomaUrl;
    }

    public String getXxwUrl() {
        return xxwUrl;
    }

    public void setXxwUrl(String xxwUrl) {
        this.xxwUrl = xxwUrl;
    }

    public String getYdsUrl() {
        return ydsUrl;
    }

    public void setYdsUrl(String ydsUrl) {
        this.ydsUrl = ydsUrl;
    }

    public String getCerator() {
        return cerator;
    }

    public void setCerator(String cerator) {
        this.cerator = cerator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateStr() {
        switch (this.getState()){
            case SignUp.STATE_CENTER_WAIT:
                return "待审核";
            case SignUp.STATE_CENTER_NOT:
                return "中心未通过";
            case SignUp.STATE_SCHOOL_WAIT:
                return "高校审核中";
            case SignUp.STATE_SCHOOL_NOT:
                return "高校未通过";
            case SignUp.STATE_SCHOOL_PASS:
                return "高校已通过";
            default:
                return "";
        }
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
