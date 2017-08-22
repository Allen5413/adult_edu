package com.allen.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/5.
 */
@ConfigurationProperties(prefix = "edu", locations = "classpath:config.properties")
public class ConfigProp {
    public Map<String, String> domain = new HashMap<String, String>();
    public Map<String, String> center = new HashMap<String, String>();
    public Map<String, String> signUp = new HashMap<String, String>();
    public Map<String, String> student = new HashMap<String, String>();
    public Map<String, String> attop = new HashMap<String, String>();
    public Map<String, String> school = new HashMap<String, String>();

    public Map<String, String> getCenter() {
        return center;
    }

    public void setCenter(Map<String, String> center) {
        this.center = center;
    }

    public Map<String, String> getSignUp() {
        return signUp;
    }

    public void setSignUp(Map<String, String> signUp) {
        this.signUp = signUp;
    }

    public Map<String, String> getStudent() {
        return student;
    }

    public void setStudent(Map<String, String> student) {
        this.student = student;
    }

    public Map<String, String> getAttop() {
        return attop;
    }

    public void setAttop(Map<String, String> attop) {
        this.attop = attop;
    }

    public Map<String, String> getSchool() {
        return school;
    }

    public void setSchool(Map<String, String> school) {
        this.school = school;
    }

    public Map<String, String> getDomain() {
        return domain;
    }

    public void setDomain(Map<String, String> domain) {
        this.domain = domain;
    }
}
