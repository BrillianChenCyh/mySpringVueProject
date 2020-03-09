package com.cyh.project.service;

import java.io.File;

public interface EmailService {
    /**
     * 发送简单邮件
     * @Author chenyinghui
     * @param sendTo :
     * @param title :
     * @param centent :
     * @return void
     * @throws
     * @Date 2019/11/21 21:26
     */
    void sendSimpleMail(String sendTo,String title,String centent);

    /**
     * 发送带附件的邮件
     * @Author chenyinghui
     * @param sendTo :
     * @param title :
     * @param centent :
     * @param file :
     * @return void
     * @throws
     * @Date 2019/11/21 21:27
     */
    void sendAttachMail(String sendTo, String title, String centent, File file);
}
