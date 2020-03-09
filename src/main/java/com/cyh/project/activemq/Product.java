package com.cyh.project.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


/**
 *  @author: chenyinghui
 *  @Date: 2019/12/18 18:04
 *  @Description: 生产者
 */
@Component
public class Product {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //发送消息
    public void sendMessage(Destination destination, String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
