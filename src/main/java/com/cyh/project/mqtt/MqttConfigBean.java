package com.cyh.project.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  @author: chenyinghui
 *  @Date: 2020/3/17 10:34
 *  @Description: MQTT配置对象
 */
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfigBean {

    private String host;

    private String topic;

    private String username;

    private String password;

    private int timeout;

    private int keepalive;

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the keepalive
	 */
	public int getKeepalive() {
		return keepalive;
	}

	/**
	 * @param keepalive the keepalive to set
	 */
	public void setKeepalive(int keepalive) {
		this.keepalive = keepalive;
	}

	@Override
	public String toString() {
		return "MqttConfigBean [host=" + host + ", topic=" + topic + ", username=" + username + ", password=" + password
				+ ", timeout=" + timeout + ", keepalive=" + keepalive + "]";
	}

}
