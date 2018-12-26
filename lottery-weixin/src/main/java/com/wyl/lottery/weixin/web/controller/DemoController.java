package com.wyl.lottery.weixin.web.controller;

import com.wyl.lottery.weixin.api.userapis.WxOAuthApis;
import com.wyl.lottery.weixin.api.userapis.bean.OauthToken;
import com.wyl.lottery.weixin.api.userapis.bean.WxUserInfo;
import com.wyl.lottery.weixin.web.config.WeixinConfigPorperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${ksudi.context-path}/demo")
public class DemoController extends AbstractWeixinSupport {
    @Autowired
    WeixinConfigPorperties weixinConfigPorperties;

    @GetMapping("/checkOauth")
    public String checkOauth(Model model, String code, String state, String redirectto) {
        model.addAttribute("code", code);
        System.out.println(code);
        System.out.println(state);
        System.out.println(redirectto);
        OauthToken oauthToken = WxOAuthApis.getAccessToken(weixinConfigPorperties.getAppid(),
                weixinConfigPorperties.getSecret(), code);

        WxUserInfo wxUserInfo = WxOAuthApis.getUserInfo(oauthToken.getAccess_token(),oauthToken.getOpenid());
        System.out.println(wxUserInfo);
        return "redirect:" + redirectto;
    }

    @GetMapping("/index")
    public String index(Model model, String code, String state) {
        return "index";
    }
}
