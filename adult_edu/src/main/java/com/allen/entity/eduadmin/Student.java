package com.allen.entity.eduadmin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Allen on 2017/7/27.
 */
@Entity
@Table(name = "student")
public class Student {

    public final static int STATE_NORMAL = 0;       //在读
    public final static int STATE_REST = 1;         //休学
    public final static int STATE_QUIT = 2;         //退学
    public final static int STATE_BY = 3;           //毕业

    public final static int FEE_STATE_NOT = 0;       //未交
    public final static int FEE_STATE_ING = 1;         //未缴完
    public final static int FEE_STATE_OVER = 2;         //已结清

    @Id
    @GeneratedValue
    private long id;
    private long centerId;
    private long userId;
    private long schoolId;
    private long recruitTypeId;
    private long levelId;
    private long specId;
    private long teachPlanId;
    private int state;
    private int feeState;                       //费用缴清状态[0：未交，1：未缴完，2：已结清]
    private String code;
    private String name;
    private int sex;                            //性别[0：男；1：女]
    private String idCard;
    private String birthday;
    private Timestamp signUpDate;
    private Integer studyType;                  //学习方式[0：脱产、1：业余、2：函授、3：远程教育]
    private String zipCode;
    private String phone;
    private String tel;
    private String email;
    private String address;
    private String photoUrl;                 //上传照片地址
    private String idCardFrontUrl;           //身份证正面照片地址
    private String idCardBackUrl;            //身份证反面照片地址
    private String diplomaUrl;               //学历证书照片地址
    private String xxwUrl;                   //学信网认证照片url
    private String ydsUrl;                   //异地生证明
    private String zkzFrontUrl;              //准考证正面url
    private String zkzBackUrl;               //准考证反面url
    private String cerator;
    private Date createTime = new Date();       //创建时间
    private String operator;                    //操作人
    private Date operateTime = new Date();      //操作时间

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Timestamp getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Timestamp signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getZkzFrontUrl() {
        return zkzFrontUrl;
    }

    public void setZkzFrontUrl(String zkzFrontUrl) {
        this.zkzFrontUrl = zkzFrontUrl;
    }

    public String getZkzBackUrl() {
        return zkzBackUrl;
    }

    public void setZkzBackUrl(String zkzBackUrl) {
        this.zkzBackUrl = zkzBackUrl;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getStudyType() {
        return studyType;
    }

    public void setStudyType(Integer studyType) {
        this.studyType = studyType;
    }

    public int getFeeState() {
        return feeState;
    }

    public void setFeeState(int feeState) {
        this.feeState = feeState;
    }
}
