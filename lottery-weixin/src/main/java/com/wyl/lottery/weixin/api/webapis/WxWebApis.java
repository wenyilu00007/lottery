package com.wyl.lottery.weixin.api.webapis;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.utils.SignUtil;
import com.wyl.lottery.weixin.api.webapis.bean.Signature;
import com.wyl.lottery.weixin.api.webapis.bean.WxJsapiResponse;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * The type Wx web apis.
 *
 * @author Tony
 * @date 2018 -10-27 13:20:53
 */
public class WxWebApis {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxWebApis.class);

    /**
     * The constant JSAPI_TICKET_URL.
     */
    private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";

    /**
     * Gets jsapi ticket.
     *
     * @param accesstoken
     *         the accesstoken
     *
     * @return the jsapi ticket
     *
     * @author Tony
     * @date 2018 -11-08 10:10:33
     */
    public static String getJsapiTicket(String accesstoken) {
        String response = HttpUtil.doGet(MessageFormat.format(JSAPI_TICKET_URL, accesstoken));
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            WxJsapiResponse wxJsapiResponse = JSONObject.parseObject(response, WxJsapiResponse.class);
            if (0 == wxJsapiResponse.getErrcode()) {
                return wxJsapiResponse.getTicket();
            }
        }
        return null;
    }

    /**
     * 获取js-sdk所需的签名，简化逻辑
     *
     * @param appid
     *         the appid
     * @param accesstoken
     *         the accesstoken
     * @param url
     *         the url
     *
     * @return the signature
     *
     * @author huff
     * @date 2016年5月27日 -11-08 10:10:33
     */
    public static Signature getSignature(String appid, String accesstoken, String url) {
        LOGGER.info("========================进行js签名 - url：" + url);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String noncestr = UUID.randomUUID().toString().replaceAll("-", "");
        return getSignature(appid, accesstoken, url, noncestr, timestamp);
    }

    /**
     * 获取js-sdk所需的签名
     *
     * @param appid
     *         the appid
     * @param accesstoken
     *         the accesstoken
     * @param url
     *         the url
     * @param noncestr
     *         随机字符串
     * @param timestamp
     *         时间戳
     *
     * @return the signature
     *
     * @author huff
     * @date 2016年5月27日 -11-08 10:10:33
     */
    public static Signature getSignature(String appid, String accesstoken, String url, String noncestr, String timestamp) {
        String jsapi_ticket = getJsapiTicket(accesstoken);
        String sign = SignUtil.getSignature(jsapi_ticket, noncestr, timestamp, url);
        Signature signature = new Signature();
        signature.setNoncestr(noncestr);
        signature.setSignature(sign);
        signature.setTimestamp(timestamp);
        signature.setAppid(appid);
        return signature;
    }
}
