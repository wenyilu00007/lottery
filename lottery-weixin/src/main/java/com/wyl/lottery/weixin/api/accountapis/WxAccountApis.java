package com.wyl.lottery.weixin.api.accountapis;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.accountapis.bean.WxQrcode;
import com.wyl.lottery.weixin.api.accountapis.bean.WxQrcodeRequest;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 账户管理apis
 *
 * @author Tony
 * @date 2018 -11-01 18:52:55
 */
public class WxAccountApis {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxAccountApis.class);

    /**
     * 生成二维码url
     */
    private static final String QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";

    /**
     * 生成二维码
     *
     * @param accesstoken
     *         the accesstoken
     * @param wxQrcodeRequest
     *         the wx qrcode request
     *
     * @return the wx qrcode
     *
     * @author Tony
     * @date 2018 -11-01 18:52:55
     */
    public static WxQrcode createQrcode(String accesstoken, WxQrcodeRequest wxQrcodeRequest) {
        String response = HttpUtil.doPost(MessageFormat.format(QRCODE_CREATE, accesstoken), JSONObject.toJSONString(wxQrcodeRequest), 1);
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            return JSONObject.parseObject(response, WxQrcode.class);
        }
        return null;
    }
}
