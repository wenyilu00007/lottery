package com.wyl.lottery.weixin.web.ms;

import com.wyl.lottery.weixin.api.userapis.WxUserApis;
import com.wyl.lottery.weixin.api.userapis.bean.WxUserInfo;
import com.wyl.lottery.weixin.client.bo.WxUserInfoBO;
import com.wyl.lottery.weixin.web.config.WeixinConfigPorperties;
import com.wyl.lottery.weixin.web.controller.AbstractWeixinSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User info micro service.
 *
 * @author Tony
 * @date 2018 -10-31 18:43:13
 */
@Api(value = "用户", tags = "用户相关")
@RestController
@RequestMapping("${server.servlet.context-path}")
public class UserInfoMicroService extends AbstractWeixinSupport {
    /**
     * The Weixin config porperties.
     */
    @Autowired
    private WeixinConfigPorperties weixinConfigPorperties;

    /**
     * Gets user info.
     *
     * @param openid
     *         the openid
     *
     * @return the user info
     *
     * @author Tony
     * @date 2018 -10-31 18:43:13
     */
    @ApiOperation("根据openid获取用户基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("ms/userinfo/userinfo/v1")
    public WxUserInfoBO getUserInfo(@RequestParam("openid") String openid) {
        WxUserInfo userInfo = WxUserApis.getUserInfo(getAccessToken(weixinConfigPorperties.getAppid(), weixinConfigPorperties.getSecret()),
                openid);
        WxUserInfoBO wxUserInfoBO = new WxUserInfoBO();
        wxUserInfoBO.setOpenid(openid);
        wxUserInfoBO.setNickname(userInfo.getNickname());
        wxUserInfoBO.setHeadimgurl(userInfo.getHeadimgurl());
        return wxUserInfoBO;
    }
}
