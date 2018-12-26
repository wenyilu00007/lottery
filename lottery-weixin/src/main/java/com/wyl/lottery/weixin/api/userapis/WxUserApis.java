package com.wyl.lottery.weixin.api.userapis;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.base.WxResponse;
import com.wyl.lottery.weixin.api.userapis.bean.WxUserInfo;
import com.wyl.lottery.weixin.api.userapis.bean.WxUserInfoList;
import com.wyl.lottery.weixin.api.userapis.bean.WxUserList;
import com.wyl.lotterycommon.utils.http.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Wx user apis.
 *
 * @author Tony
 * @date 2018 -10-26 17:18:27
 */
public class WxUserApis {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxUserApis.class);

    /**
     * The constant GET_URL.
     */
    private static final String GET_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={0}&next_openid={1}";
    /**
     * The constant GET_INFO_URL.
     */
    private static final String GET_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
    /**
     * The constant GET_INFO_BATCH_URL.
     */
    private static final String GET_INFO_BATCH_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token={0}";

    /**
     * 获取用户列表.
     *
     * @param accesstoken
     *         the accesstoken
     *
     * @return the users
     *
     * @author Tony
     * @date 2018 -10-26 17:18:27
     */
    public static List<String> getUsers(String accesstoken) {
        List<String> result = new ArrayList<>();
        String nextOpenid = "";
        int count = 0;
        boolean stopFlag = false;
        do {
            WxUserList wxUserList = getUsers(accesstoken, nextOpenid);
            count += wxUserList.getCount();
            nextOpenid = wxUserList.getNext_openid();
            result.addAll(wxUserList.getData().getOpenid());
            if (count < wxUserList.getTotal()) {
                stopFlag = true;
            }
        } while (stopFlag);
        return result;
    }

    /**
     * 获取用户列表.
     *
     * @param accesstoken
     *         the accesstoken
     * @param nextOpenid
     *         the next openid
     *
     * @return the users
     *
     * @author Tony
     * @date 2018 -10-26 17:18:27
     */
    public static WxUserList getUsers(String accesstoken, String nextOpenid) {
        String response = HttpUtil.doGet(MessageFormat.format(GET_URL, accesstoken, nextOpenid));
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            if (!response.contains(WxResponse.ERRCODE)) {
                WxUserList wxUserList = JSONObject.parseObject(response, WxUserList.class);
                return wxUserList;
            }
        }
        return null;
    }


    /**
     * 获取用户信息.
     *
     * @param accesstoken
     *         the accesstoken
     * @param openid
     *         the openid
     *
     * @return the wx user info
     *
     * @author Tony
     * @date 2018 -10-26 17:52:30
     */
    public static WxUserInfo getUserInfo(String accesstoken, String openid) {
        String response = HttpUtil.doGet(MessageFormat.format(GET_INFO_URL, accesstoken, openid));
        if (StringUtils.isNotEmpty(response)) {
            LOGGER.info(response);
            if (!response.contains(WxResponse.ERRCODE)) {
                return JSONObject.parseObject(response, WxUserInfo.class);
            }
        }
        return null;
    }

    /**
     * 批量获取用户信息.
     *
     * @param accesstoken
     *         the accesstoken
     * @param openids
     *         the openids 最多传入100个
     *
     * @return the list
     *
     * @author Tony
     * @date 2018 -10-26 17:59:34
     */
    public static List<WxUserInfo> getUserInfos(String accesstoken, String... openids) {
        if (openids.length > 0) {
            Map<String, List> request = new HashMap<>(1);
            List<Map> userList = new ArrayList<>();
            for (String openid : openids) {
                Map<String, String> user = new HashMap<>(2);
                user.put("openid",openid);
                user.put("lang","zh_CN");
                userList.add(user);
            }
            request.put("user_list", userList);
            System.out.println(JSONObject.toJSONString(request));
            String response = HttpUtil.doPost(MessageFormat.format(GET_INFO_BATCH_URL, accesstoken),
                    JSONObject.toJSONString(request), 1);
            if (StringUtils.isNotEmpty(response)) {
                LOGGER.info(response);
                if (!response.contains(WxResponse.ERRCODE)) {
                    WxUserInfoList wxUserInfoList = JSONObject.parseObject(response, WxUserInfoList.class);
                    return wxUserInfoList.getUser_info_list();
                }
            }
        }
        return null;
    }

}
