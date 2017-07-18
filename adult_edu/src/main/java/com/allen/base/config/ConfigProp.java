package com.allen.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/5.
 */
@ConfigurationProperties(prefix = "edu", locations = "classpath:config.properties")
public class ConfigProp {
    public Map<String, String> centerLogo = new HashMap<String, String>();
    public Map<String, String> centerBanner = new HashMap<String, String>();

    public Map<String, String> getCenterLogo() {
        return centerLogo;
    }

    public void setCenterLogo(Map<String, String> centerLogo) {
        this.centerLogo = centerLogo;
    }

    public Map<String, String> getCenterBanner() {
        return centerBanner;
    }

    public void setCenterBanner(Map<String, String> centerBanner) {
        this.centerBanner = centerBanner;
    }
}
