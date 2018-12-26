package com.wyl.lottery.weixin.api.userapis;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.tokenapis.bean.WxAccessTokenRequest;
import com.wyl.lottery.weixin.api.userapis.bean.OauthToken;
import com.wyl.lottery.weixin.api.userapis.bean.WxUserInfo;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Map;

/**
 * 网页授权.
 *
 * @author Tony
 * @date 2018 -10-27 13:22:50
 */
public class WxOAuthApis {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxOAuthApis.class);

    private static final String URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&{3}#wechat_redirect";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";

    public static String getUrl(String appid, String redirectUrl, String scope, String extParam) {
        try {
            return MessageFormat.format(URL, appid, URLEncoder.encode(redirectUrl, "utf-8"), scope, extParam);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return redirectUrl;
    }

    public static OauthToken getAccessToken(String appid, String secret, String code) {
        String response = HttpUtil.doGet(MessageFormat.format(ACCESS_TOKEN_URL, appid, secret, code));
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            // 返回值转成map
            Map responseMap = JSONObject.parseObject(response, Map.class);
            if (responseMap.get(WxAccessTokenRequest.ERRCODE) == null) {
                OauthToken oauthToken = JSONObject.parseObject(response, OauthToken.class);
                return oauthToken;
            }
        }
        return null;
    }

    public static WxUserInfo getUserInfo(String accesstoken, String openid){
        String response = HttpUtil.doGet(MessageFormat.format(USER_INFO_URL, accesstoken, openid));
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            // 返回值转成map
            Map responseMap = JSONObject.parseObject(response, Map.class);
            if (responseMap.get(WxAccessTokenRequest.ERRCODE) == null) {
                WxUserInfo wxUserInfo = JSONObject.parseObject(response, WxUserInfo.class);
                return wxUserInfo;
            }
        }
        return null;
    }
}
