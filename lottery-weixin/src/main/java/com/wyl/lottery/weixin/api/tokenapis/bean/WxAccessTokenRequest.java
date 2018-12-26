package com.wyl.lottery.weixin.api.tokenapis.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 获取accesstoken的入参.
 *
 * @author Tony
 * @date 2018 -10-26 10:09:13
 */
@Getter
@Setter
public class WxAccessTokenRequest implements Serializable {
    public static final String GRANT_TYPE = "grant_type";
    public static final String ERRCODE = "errcode";
    /**
     * The Grant type.
     */
    private String grant_type = "client_credential";
    /**
     * The Appid.
     */
    private String appid;
    /**
     * The Secret.
     */
    private String secret;

    public WxAccessTokenRequest() {
    }

    public WxAccessTokenRequest(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    public String generateUrlParam() {
        return "?grant_type=" + this.grant_type + "&appid=" + this.appid + "&secret=" + this.secret;
    }

}
