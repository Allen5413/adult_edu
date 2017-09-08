package com.allen.entity.datachange;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 数据变更信息
 * Created by Allen on 2016/12/12.
 */
@Entity
@Table(name = "data_change")
public class DataChange {

    public final static int STATE_AUDIT_WAIT = 0;       //待审
    public final static int STATE_AUDIT_NOT = 1;        //不通过
    public final static int STATE_AUDIT_PASS = 2;       //通过

    public final static int TYPE_ADD = 0;               //新增
    public final static int TYPE_EDIT = 1;              //修改
    public final static int TYPE_DEL = 2;               //删除
    public final static int TYPE_NOTICE = 3;            //新发的通知公告

    @Id
    @GeneratedValue
    private long id;
    private long centerId;                          //中心id
    private long studentId;                         //学号
    private String studentName;                     //被更改的学生姓名
    private String changeContent;                   //更改内容
    private int state;                              //状态
    private int type;                               //类型
    private String refuseContent;                   //拒绝通过原因
    private String changeTable;                     //更改的表名
    private long changeTableId;                     //更改表的数据id
    private String changeTableField;                //更改表的字段
    private long creatorId;                         //更改人id
    private Date createTime = new Date();           //更改时间
    private String editReson;                       //变更原因

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRefuseContent() {
        return refuseContent;
    }

    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent;
    }

    public String getChangeTable() {
        return changeTable;
    }

    public void setChangeTable(String changeTable) {
        this.changeTable = changeTable;
    }

    public long getChangeTableId() {
        return changeTableId;
    }

    public void setChangeTableId(long changeTableId) {
        this.changeTableId = changeTableId;
    }

    public String getChangeTableField() {
        return changeTableField;
    }

    public void setChangeTableField(String changeTableField) {
        this.changeTableField = changeTableField;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getEditReson() {
        return editReson;
    }

    public void setEditReson(String editReson) {
        this.editReson = editReson;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
