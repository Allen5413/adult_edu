package com.allen.entity.recruit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报名信息表
 * Created by Allen on 2017/6/29.
 */
@Entity
@Table(name = "sign_up")
public class SignUp {
    @Id
    @GeneratedValue
    private long id;
}
