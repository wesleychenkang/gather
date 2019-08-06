package com.zhongdao.gather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms")
@PropertySource("classpath:config/sms.properties")
public class SMSCode {

    private String accessKey;
    private String scretKey;
    private String templateCode;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getScretKey() {
        return scretKey;
    }

    public void setScretKey(String scretKey) {
        this.scretKey = scretKey;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
