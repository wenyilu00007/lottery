package com.wyl.lottery.weixin.api.tokenapis;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.tokenapis.bean.AccessToken;
import com.wyl.lottery.weixin.api.tokenapis.bean.WxAccessTokenRequest;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * The type Wx token apis.
 *
 * @author Tony
 * @date 2018 -10-26 17:19:16
 */
public class WxTokenApis {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxTokenApis.class);
    /**
     * The constant URL.
     */
    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * Gets access token.
     *
     * @param wxAccessTokenRequest
     *         the wx access token request
     *
     * @return the access token
     *
     * @author Tony
     * @date 2018 -10-26 17:19:16
     */
    public static AccessToken getAccessToken(WxAccessTokenRequest wxAccessTokenRequest) {
        String response = HttpUtil.doGet(URL + wxAccessTokenRequest.generateUrlParam());
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            // 返回值转成map
            Map responseMap = JSONObject.parseObject(response, Map.class);
            if (responseMap.get(WxAccessTokenRequest.ERRCODE) == null) {
                AccessToken accessToken = JSONObject.parseObject(response, AccessToken.class);
                return accessToken;
            }
        }
        return null;
    }
}
