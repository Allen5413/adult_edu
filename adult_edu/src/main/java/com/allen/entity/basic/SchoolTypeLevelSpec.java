package com.allen.entity.basic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 一个学校的招生类型的层次关联的所属专业
 * Created by Allen on 2017/7/7.
 */
@Entity
@Table(name = "school_type_level_spec")
public class SchoolTypeLevelSpec {
    @Id
    @GeneratedValue
    private long id;
    private long schoolId;
    private long recruitTypeId;
    private long levelId;
    private long specId;
    private String operator;
    private Date operateTime = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
