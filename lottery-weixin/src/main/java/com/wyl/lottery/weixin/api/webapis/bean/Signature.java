package com.wyl.lottery.weixin.api.webapis.bean;

import lombok.Data;

/**
 * The type Signature.
 *
 * @author Tony
 * @date 2018 -11-01 17:27:29
 */
@Data
public class Signature {

	/**
	 * The Appid.
	 */
	private String appid;
	/**
	 * The Timestamp.
	 */
	private String timestamp;
	/**
	 * The Noncestr.
	 */
	private String noncestr;
	/**
	 * The Signature.
	 */
	private String signature;

}
