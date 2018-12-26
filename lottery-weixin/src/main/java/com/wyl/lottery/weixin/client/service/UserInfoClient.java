package com.wyl.lottery.weixin.client.service;

import com.wyl.lottery.weixin.client.bo.WxUserInfoBO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface User info client.
 *
 * @author Tony
 * @date 2018 -10-31 18:59:02
 */
public interface UserInfoClient {
    /**
     * Gets user info.
     *
     * @param openid
     *         the openid
     *
     * @return the user info
     *
     * @author Tony
     * @date 2018 -10-31 18:59:02
     */
    @GetMapping("ms/userinfo/userinfo/v1")
    WxUserInfoBO getUserInfo(@RequestParam("openid") String openid);

}
