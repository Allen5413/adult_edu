package com.allen.entity.basic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 一个学校的招生类型的层次关联的专业下的所有课程
 * Created by Allen on 2017/7/7.
 */
@Entity
@Table(name = "school_type_level_spec_course")
public class SchoolTypeLevelSpecCourse {
    @Id
    @GeneratedValue
    private long id;
    private long schoolTypeLevelSpecId;
    private long courseId;
    private String operator;
    private Date operateTime = new Date();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSchoolTypeLevelSpecId() {
        return schoolTypeLevelSpecId;
    }

    public void setSchoolTypeLevelSpecId(long schoolTypeLevelSpecId) {
        this.schoolTypeLevelSpecId = schoolTypeLevelSpecId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
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
