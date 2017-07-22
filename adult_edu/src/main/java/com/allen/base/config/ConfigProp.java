package com.allen.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/5.
 */
@ConfigurationProperties(prefix = "edu", locations = "classpath:config.properties")
public class ConfigProp {
    public Map<String, String> center = new HashMap<String, String>();
    public Map<String, String> signUp = new HashMap<String, String>();

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
}
