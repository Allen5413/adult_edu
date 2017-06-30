package com.allen.entity.user;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Allen on 2017/6/27.
 */
@Entity
@Table(name = "center")
public class Center {

    public static final int FEESTATE_NOT = 0;       //未缴费
    public static final int FEESTATE_OVERDUE = 1;   //要过期
    public static final int FEESTATE_YES = 2;       //已缴费

    public static final int STATE_NOT = 0;          //停用
    public static final int STATE_YES = 1;          //启用

    @Id
    @GeneratedValue
    private Long id;                            //主键
    private String code;
    private String name;
    private String linkman;
    private String phone;
    private Timestamp authorizeDate;
    private int feeState;
    private int state;
    private String remark;
    private String cerator;
    private Date createTime = new Date();       //创建时间
    private String operator;                    //操作人
    private Date operateTime = new Date();      //操作时间

    @Transient
    private String feeStateStr;
    @Transient
    private String stateStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getAuthorizeDate() {
        return authorizeDate;
    }

    public void setAuthorizeDate(Timestamp authorizeDate) {
        this.authorizeDate = authorizeDate;
    }

    public int getFeeState() {
        return feeState;
    }

    public void setFeeState(int feeState) {
        this.feeState = feeState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCerator() {
        return cerator;
    }

    public void setCerator(String cerator) {
        this.cerator = cerator;
    }

    public String getFeeStateStr() {
        switch (this.getFeeState()){
            case FEESTATE_NOT:
                return "未缴费";
            case FEESTATE_YES:
                return "已缴费";
            default:
                return "未知";
        }
    }

    public String getStateStr() {
        switch (this.getState()){
            case STATE_NOT:
                return "停用";
            case STATE_YES:
                return "启用";
            default:
                return "未知";
        }
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
}
