package com.wyl.lottery.weixin.web.vo;



/**
 * @Title Weixinbo.java
 * @Package com.ksudi.open.api.vo
 * @Description
 * @author 侯冠群
 * @date 2018年12月6日下午4:18:38
 * @version
 */
public class WeixinVO {
	public static final String CUSTOMERNUMBER = "customernumber";
	public static final String PACKAGENUM = "packagenum";
	public static final String EXPRESSID = "expressid";
	public static final String ORDERSIDS = "ordersids";
	private String customernumber;
	private Integer packagenum;
	private Long expressid;
	private Long[] ordersids;

	public String getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(String customernumber) {
		this.customernumber = customernumber;
	}

	public Integer getPackagenum() {
		return packagenum;
	}

	public void setPackagenum(Integer packagenum) {
		this.packagenum = packagenum;
	}

	public Long getExpressid() {
		return expressid;
	}

	public void setExpressid(Long expressid) {
		this.expressid = expressid;
	}

	public Long[] getOrdersids() {
		return ordersids;
	}

	public void setOrdersids(Long[] ordersids) {
		this.ordersids = ordersids;
	}

}
