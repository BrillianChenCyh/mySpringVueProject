package com.cyh.project.mqtt;

import com.alibaba.fastjson.JSONObject;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
/**
 *  @author: chenyinghui
 *  @Date: 2020/3/17 10:32
 *  @Description: Mqtt消息接收处理2
 */
public class MqttMessageHandler2 implements MessageHandler {
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		String messageStr = (String) message.getPayload();
		JSONObject jsonMessage = JSONObject.parseObject(messageStr);
		String mn = jsonMessage.getString("MN");
		Integer sn = jsonMessage.getInteger("CN");
		System.out.println("client2:"+mn);
		System.out.println("client2:"+sn);
	}

}
