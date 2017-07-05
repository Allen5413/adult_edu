package com.allen.entity.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Allen on 2017/6/26.
 */
@Entity
@Table(name = "user")
public class User {
    public static final int STATE_DELETE = 0;     //删除
    public static final int STATE_ENABLE = 1;     //启用
    public static final int STATE_DISABLE = 2;    //停用

    public static final int TYPE_ZS_ADMIN = 0;
    public static final int TYPE_CENTER_ADMIN = 1;
    public static final int TYPE_CENTER_CHAILD = 2;
    public static final int TYPE_FXS = 3;
    public static final int TYPE_STUDENT = 4;

    public static final int ISOPERATEAUDIT_NOT = 0;
    public static final int ISOPERATEAUDIT_YES = 1;

    @Id
    @GeneratedValue
    private Long id;                            //主键
    private String loginName;
    private String pwd;
    private String name;                        //姓名
    private String phone;                       //手机
    private Integer state;                      //用户状态
    private Integer type;                       //用户类型[0：超级管理员(至善用户，分配中心账号)；1：中心管理员；2：中心子账户；3：分销商；4：学生]
    private String code;                        //编码
    private String address;                     //详细地址
    private String linkMan;                     //联系人
    private Long centerId;                      //中心id
    private int isOperateAudit;                 //操作后是否需要中心管理员审核
    private String creator;                     //创建人
    private Date createTime = new Date();       //创建时间
    private String operator;                    //操作人
    private Date operateTime = new Date();      //操作时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public int getIsOperateAudit() {
        return isOperateAudit;
    }

    public void setIsOperateAudit(int isOperateAudit) {
        this.isOperateAudit = isOperateAudit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }
}
