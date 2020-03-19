package com.cyh.project.mqtt;

import com.cyh.project.util.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

/**
 *  @author: chenyinghui
 *  @Date: 2020/3/17 10:33
 *  @Description: MQTT客户端订阅配置-按照污染源弄的
 */
//@Configuration
public class MqttConfiguration {

	@Autowired
	private MqttConfigBean mqttBean;

	private String clientId;

//    private String clientId2;

	/**
	 * 配置client,监听的topic
	 */
	@Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }
//    @Bean
//    public MessageChannel mqttInputChannel2() {
//        return new DirectChannel();
//    }

	@Bean
    public MqttPahoClientFactory mqttClientFactory() {
        clientId = "wry_" + IdGen.uuid();
//        clientId2 = "wry_" + IdGen.uuid();
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs(mqttBean.getHost());
        factory.setUserName(mqttBean.getUsername());
        factory.setPassword(mqttBean.getPassword());
        factory.setConnectionTimeout(mqttBean.getTimeout());
        factory.setKeepAliveInterval(mqttBean.getKeepalive());
        return factory;
    }

	@Bean
    public IntegrationFlow mqttInFlow() {
        System.out.println(clientId);
        return IntegrationFlows.from(mqttInbound())
              //  .transform(p -> p + ", received from MQTT")
                .handle(new MqttMessageHandler())
                .get();
    }
//	@Bean
//    public IntegrationFlow mqttInFlow2() {
//        System.out.println(clientId2);
//        return IntegrationFlows.from(mqttInbound2())
//              //  .transform(p -> p + ", received from MQTT")
//                .handle(new MqttMessageHandler2())
//                .get();
//    }

    /**
     * 配置client,监听的topic
     */
    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId,
                mqttClientFactory(), mqttBean.getTopic());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }
//    @Bean
//    public MessageProducerSupport mqttInbound2() {
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId2,
//                mqttClientFactory(), mqttBean.getTopic());
//        adapter.setCompletionTimeout(5000);
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(1);
//        return adapter;
//    }


}
