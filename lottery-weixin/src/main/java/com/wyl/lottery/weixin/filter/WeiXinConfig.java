package com.wyl.lottery.weixin.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wyl.weixin",ignoreInvalidFields = true)
public class WeiXinConfig {
	/**
	 * appid
	 */
	private String appid;
	/**
	 * secret
	 */
	private String secret;
	/**
	 * token
	 */
	private String token;

	/**
	 * baseurl
	 */
	private String baseurl;

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
