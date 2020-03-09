package com.cyh.project.service.impl;

import com.cyh.project.config.EmailConfig;
import com.cyh.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 *  @author: chenyinghui
 *  @Date: 2019/11/17 18:44
 *  @Description:
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String sendTo, String title, String centent) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发送者
        mailMessage.setFrom(emailConfig.getEmailFrom());
        //发送对象
        mailMessage.setTo(sendTo);
        mailMessage.setSubject(title);
        mailMessage.setText(centent);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendAttachMail(String sendTo, String title, String centent, File file) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg,true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(centent);
            FileSystemResource r = new FileSystemResource(file);
            helper.addAttachment("附件",r);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(msg);
    }
}
