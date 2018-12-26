package com.wyl.lottery.weixin.api.menuapis;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.base.WxResponse;
import com.wyl.lottery.weixin.api.menuapis.bean.WxMenuRequest;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 菜单apis
 *
 * @author Tony
 * @date 2018 -10-26 16:27:48
 */
public class WxMenuApis {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxMenuApis.class);

    /**
     * 生成菜单url
     */
    private static final String CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}";
    /**
     * 获取菜单url
     */
    private static final String GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token={0}";
    /**
     * 删除菜单url
     */
    private static final String DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={0}";

    /**
     * 创建菜单.
     *
     * @param accesstoken
     *         the accesstoken
     * @param wxMenuRequest
     *         the menu request
     *
     * @return the menu response
     *
     * @author Tony
     * @date 2018 -10-26 16:27:48
     */
    public static WxResponse createMenu(String accesstoken, WxMenuRequest wxMenuRequest) {
        String response = HttpUtil.doPost(MessageFormat.format(CREATE_URL, accesstoken), JSONObject.toJSONString(wxMenuRequest), 1);
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            return JSONObject.parseObject(response, WxResponse.class);
        }
        return null;
    }

    /**
     * 查询菜单.
     *
     * @param accesstoken
     *         the accesstoken
     *
     * @return the menu
     *
     * @author Tony
     * @date 2018 -10-26 16:27:48
     */
    public static String getMenu(String accesstoken) {
        String response = HttpUtil.doGet(MessageFormat.format(GET_URL, accesstoken));
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            return response;
        }
        return null;
    }

    /**
     * 删除菜单.
     *
     * @param accesstoken
     *         the accesstoken
     *
     * @return the menu response
     *
     * @author Tony
     * @date 2018 -10-26 16:29:47
     */
    public static WxResponse deleteMenu(String accesstoken){
        String response = HttpUtil.doGet(MessageFormat.format(DELETE_URL, accesstoken));
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            return JSONObject.parseObject(response, WxResponse.class);
        }
        return null;
    }
}
