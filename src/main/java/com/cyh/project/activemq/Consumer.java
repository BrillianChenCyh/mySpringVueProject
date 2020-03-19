package com.cyh.project.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *  @author: chenyinghui
 *  @Date: 2020/3/7 17:39
 *  @Description: 消费者
 */
//@Component
public class Consumer {

    @JmsListener(destination = "myqueues")
    public void receiveMsg(String text){
        System.out.println(text+"............");
    }
}
