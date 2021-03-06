package com.wyl.lottery.weixin.web.controller;

import com.wyl.lottery.weixin.api.utils.SignUtil;
import com.wyl.lottery.weixin.filter.WeiXinConfig;
import com.wyl.lottery.weixin.web.ms.AccountMicroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 微信相关
 *
 * @author Jason.li
 */
@Controller
@RequestMapping(value = "${server.servlet.context-path}/weixin")
@EnableConfigurationProperties({WeiXinConfig.class})
public class WeiXinController {

    private static Logger LOGGER = LoggerFactory.getLogger(WeiXinController.class);

    private final static String BASEDIR = "weixin/";

    private final static String ERROR_MSG = "errormsg";
    @Autowired
    private WeiXinConfig weiXinConfig;
    @Autowired
    private AccountMicroService accountMicroService;

    @GetMapping("/checkOauth")
    public String checkOauth(Model model, String code, String state, String redirectto) {

        LOGGER.info("进入授权，登录,code:{},redirectto:{}", code, redirectto);

        String openid = accountMicroService.oauth(code);

        //TODO 将用户塞入session中

        try {
            redirectto = URLDecoder.decode(redirectto, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("授权后跳转url解码出错", e);
        }
        LOGGER.info("进入授权，登录,解码后redirectto:{}", redirectto);

        redirectto += (redirectto.contains("?") ? "&" : "?") + "randm=" + System.currentTimeMillis() + "&ticket=" + "123456789";
        return "redirect:" + redirectto;
    }

    /**
     * 绑定微信服务器
     *
     * @param request 请求
     * @return 响应内容
     * @author Tony
     * @date 2017年03月07日 11:28
     */
    @GetMapping(value = "/message")
    @ResponseBody
    protected final String bind(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if (isLegal(request, weiXinConfig.getToken())) {
            //绑定微信服务器成功
            return request.getParameter("echostr");
        } else {
            //绑定微信服务器失败
            return "";
        }
    }

    @PostMapping(value = "/message")
    @ResponseBody
    protected final String acceptMessage(HttpServletRequest request) {
        return "";
    }

    boolean isLegal(HttpServletRequest request, String token) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        return SignUtil.checkSignature(token, signature, timestamp, nonce);
    }


}
