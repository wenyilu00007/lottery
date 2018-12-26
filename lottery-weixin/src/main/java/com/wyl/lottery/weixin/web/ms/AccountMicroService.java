package com.wyl.lottery.weixin.web.ms;

import com.wyl.lottery.weixin.api.userapis.WxOAuthApis;
import com.wyl.lottery.weixin.api.userapis.bean.OauthToken;
import com.wyl.lottery.weixin.web.config.WeixinConfigPorperties;
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
 * 账户微服务.
 *
 * @author Tony
 * @date 2018 -10-31 17:43:49
 */
@Api(value = "账户管理", tags = "账户相关")
@RestController
@RequestMapping("${server.servlet.context-path}")
public class AccountMicroService {
    /**
     * The Weixin config porperties.
     */
    @Autowired
    private WeixinConfigPorperties weixinConfigPorperties;

    /**
     * Oauth ksd result.
     *
     * @param code
     *         授权码
     *
     * @return the ksd result
     *
     * @author Tony
     * @date 2018 -10-31 17:43:49
     */
    @ApiOperation(value = "根据授权码获取openid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "授权码", required = true, paramType = "query", dataType = "String"),
    })
    @GetMapping("/ms/account/openid/v1")
    public String oauth(@RequestParam("code") String code){
        OauthToken oauthToken = WxOAuthApis.getAccessToken(weixinConfigPorperties.getAppid(),
                weixinConfigPorperties.getSecret(), code);
        assert oauthToken != null;
        return oauthToken.getOpenid();
    }
}
