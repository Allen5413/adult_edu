package com.allen.entity.fee;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Allen on 2017/8/2.
 */
@Entity
@Table(name = "student_fee")
public class StudentFee {

    public final static int FEE_STYLE_PLATFORM = 0;
    public final static int FEE_STYLE_FXS = 1;
    public final static int FEE_STYLE_CENTER = 2;

    @Id
    @GeneratedValue
    private long id;
    private long studentId;
    private long feeTypeId;
    private int feeStyle;                       //缴费方式[0：平台缴费；1：分销商缴费；2：中心缴费]
    private long fee;                           //费用，用分来记录，避免小数转化过程造成误差
    private long fxsFee;                        //分销商实际缴费[只有缴费方式为分销商缴费的时候，这个字段才填]
    private String operator;
    private Date operateTime = new Date();
    @Transient
    private String feeStr;
    @Transient
    private String fxsFeeStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(long feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public int getFeeStyle() {
        return feeStyle;
    }

    public void setFeeStyle(int feeStyle) {
        this.feeStyle = feeStyle;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getFxsFee() {
        return fxsFee;
    }

    public void setFxsFee(long fxsFee) {
        this.fxsFee = fxsFee;
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

    public String getFeeStr() {
        return new BigDecimal(fee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getFxsFeeStr() {
        return new BigDecimal(fxsFee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
}
