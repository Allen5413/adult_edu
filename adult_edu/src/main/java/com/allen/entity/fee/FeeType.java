package com.allen.entity.fee;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用设置
 * Created by Allen on 2017/7/17.
 */
@Entity
@Table(name = "fee_type")
public class FeeType {

    public final static int TERM_SPRING = 0;    //春季
    public final static int TERM_AUTUMN = 1;    //秋季

    @Id
    @GeneratedValue
    private long id;
    private long centerId;
    private String name;
    private long fee;                           //费用，用分来记录，避免小数转化过程造成误差
    private long schoolId;
    private long typeId;
    private long levelId;
    private int year;
    private int term;
    private String creator;
    private Date createTime = new Date();
    private String operator;
    private Date operateTime = new Date();
    @Transient
    private String feeStr;
    @Transient
    private String batch;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getLevelId() {
        return levelId;
    }

    public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getFeeStr() {
        return new BigDecimal(fee).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getBatch() {
        return year+"年"+(0 == term ? "春季":"秋季");
    }
}
