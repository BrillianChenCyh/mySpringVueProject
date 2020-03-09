package com.cyh.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  @author: chenyinghui
 *  @Date: 2019/11/17 18:41
 *  @Description:
 */
@Component
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
}
