package com.wyl.lottery.weixin.api.messageapis;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.messageapis.bean.WxTempMsgRequest;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * The type Wx receive message apis.
 *
 * @author Tony
 * @date 2018 -10-27 13:35:40
 */
public class WxMessageApis {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxMessageApis.class);
    public static final String SEND_TEMPLATE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";

    public static String sendTemplateMessage(String accesstoken, WxTempMsgRequest wxTempMsgRequest){
        String response = HttpUtil.doPost(MessageFormat.format(SEND_TEMPLATE_MESSAGE_URL, accesstoken),
                JSONObject.toJSONString(wxTempMsgRequest), 1);
        System.out.println(JSONObject.toJSONString(wxTempMsgRequest));
        if (StringUtils.isNotEmpty(response)) {
            System.out.println(response);
            LOGGER.info(response);
//            return JSONObject.parseObject(response, WxResponse.class);
            return response;
        }
        return null;
    }

    public static String bulidImageMessage(){
        return null;
    }
}
