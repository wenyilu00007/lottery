package com.wyl.lottery.weixin.web.controller;

import com.wyl.lottery.weixin.api.tokenapis.WxTokenApis;
import com.wyl.lottery.weixin.api.tokenapis.bean.AccessToken;
import com.wyl.lottery.weixin.api.tokenapis.bean.WxAccessTokenRequest;
import com.wyl.lottery.weixin.api.userapis.WxOAuthApis;
import com.wyl.lottery.weixin.api.userapis.bean.OauthToken;
import com.wyl.lottery.weixin.api.utils.SignUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractWeixinSupport {

    /**
     * The constant REDIS_ACCESS_TOKEN_PREFIX.
     */
    private static final String REDIS_ACCESS_TOKEN_PREFIX = "ACCESS_TONEK_";
    private static final String REDIS_OAUTH_TOKEN_PREFIX = "OAUTH_TONEK_";
    protected static final String REDIS_COURIER_QRCODE_PREFIX = "COURIER_QRCODE_";

    /**
     * Gets access token.
     *
     * @param appid
     *         the appid
     * @param secret
     *         the secret
     *
     * @return the access token
     *
     * @author Tony
     * @date 2018 -10-26 17:19:16
     */
    protected static String getAccessToken(String appid, String secret) {
        String redisKey = REDIS_ACCESS_TOKEN_PREFIX + appid;
        // 首先检查redis中是否存在access_token
            WxAccessTokenRequest wxAccessTokenRequest = new WxAccessTokenRequest(appid, secret);
            AccessToken accessToken = WxTokenApis.getAccessToken(wxAccessTokenRequest);
            return accessToken.getAccess_token();
    }

    protected static String getOauthToken(String appid, String secret, String code) {
        String redisKey = REDIS_OAUTH_TOKEN_PREFIX + appid;
        // 首先检查redis中是否存在access_token
            OauthToken oauthToken = WxOAuthApis.getAccessToken(appid, secret, code);
            return oauthToken.getAccess_token();
    }

    boolean isLegal(HttpServletRequest request, String token) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        return SignUtil.checkSignature(token, signature, timestamp, nonce);
    }
}
