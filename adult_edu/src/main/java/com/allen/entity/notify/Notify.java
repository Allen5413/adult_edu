package com.allen.entity.notify;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Allen on 2017/8/30.
 */
@Entity
@Table(name = "notify")
public class Notify {

    public final static int TYPE_STUDY = 0;     //学习信息
    public final static int TYPE_SYSTEM = 1;    //系统消息
    public final static int TYPE_EXAM = 2;      //考试通知
    public final static int TYPE_FEE = 3;       //缴费提醒
    public final static int TYPE_EDIT = 4;      //更正
    public final static int TYPE_NORMAL = 5;    //普通

    public final static int SENDOBJECT_STUDENT = 0; //学生对象
    public final static int SENDOBJECT_FXS = 1;     //分销商对象

    public final static int STATE_WAIT = 0;
    public final static int STATE_PASS = 1;
    public final static int STATE_NOT = 2;

    @Id
    @GeneratedValue
    private long id;
    private long centerId;
    private String title;
    private String content;
    private int type;
    private Integer sendObject;
    private Long schoolId;
    private Long specId;
    private Long teachPlanId;
    private Integer feeState;
    private Integer studyState;
    private String objectRemark;                //接收对象中文说明
    private int state;
    private String refuseRemark;                //审核拒绝原因
    private long ceratorId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getSendObject() {
        return sendObject;
    }

    public void setSendObject(Integer sendObject) {
        this.sendObject = sendObject;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getTeachPlanId() {
        return teachPlanId;
    }

    public void setTeachPlanId(Long teachPlanId) {
        this.teachPlanId = teachPlanId;
    }

    public Integer getFeeState() {
        return feeState;
    }

    public void setFeeState(Integer feeState) {
        this.feeState = feeState;
    }

    public Integer getStudyState() {
        return studyState;
    }

    public void setStudyState(Integer studyState) {
        this.studyState = studyState;
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

    public String getObjectRemark() {
        return objectRemark;
    }

    public void setObjectRemark(String objectRemark) {
        this.objectRemark = objectRemark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRefuseRemark() {
        return refuseRemark;
    }

    public void setRefuseRemark(String refuseRemark) {
        this.refuseRemark = refuseRemark;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    public long getCeratorId() {
        return ceratorId;
    }

    public void setCeratorId(long ceratorId) {
        this.ceratorId = ceratorId;
    }
}
