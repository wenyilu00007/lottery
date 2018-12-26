package com.wyl.lottery.weixin.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.*;
import com.wyl.lottery.weixin.api.utils.MessageUtil;
import com.wyl.lottery.weixin.api.webapis.WxWebApis;
import com.wyl.lottery.weixin.api.webapis.bean.Signature;
import com.wyl.lottery.weixin.web.config.WeixinConfigPorperties;
import com.wyl.lottery.weixin.web.service.EventHandleService;
import com.wyl.lottery.weixin.web.service.KeyWordReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Map;

@RestController
@RequestMapping("${server.servlet.context-path}")
public class CommonController extends AbstractWeixinSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private WeixinConfigPorperties weixinConfigPorperties;
    @Autowired
    private EventHandleService eventHandleService;
    @Autowired
    private KeyWordReplyService keyWordReplyService;

    /**
     * 绑定微信服务器
     *
     * @param request
     *         请求
     *
     * @return 响应内容
     *
     * @author Tony
     * @date 2017年03月07日 11:28
     */
    @GetMapping(value = "message")
    protected final String bind(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if (isLegal(request, weixinConfigPorperties.getToken())) {
            //绑定微信服务器成功
            return request.getParameter("echostr");
        } else {
            //绑定微信服务器失败
            return "";
        }
    }

    /**
     * 微信消息交互处理
     *
     * @param request
     *         http 请求对象
     *
     * @author Tony
     * @date 2017年03月07日 11:28
     */
    @PostMapping(value = "message")
    public void message(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (isLegal(request, weixinConfigPorperties.getToken())) {
                String result = "";
                Map<String, String> map = MessageUtil.parseXml(request);
                String msgType = map.get("MsgType");
                String jsonString = JSONObject.toJSONString(map);
                LOGGER.info(jsonString);
                if (MsgType.TEXT.equals(msgType)) {
                    TextMessage textMessage = JSONObject.parseObject(jsonString, TextMessage.class);
                    LOGGER.info("========================收到文本消息：" + textMessage);
                    result = keyWordReplyService.handleKeyWord(request,textMessage);
                } else if (MsgType.IMAGE.equals(msgType)) {
                    ImageMessage imageMessage = JSONObject.parseObject(jsonString, ImageMessage.class);
                    LOGGER.info("========================收到图片消息：" + imageMessage);
                } else if (MsgType.VOICE.equals(msgType)) {
                    VoiceMessage voiceMessage = JSONObject.parseObject(jsonString, VoiceMessage.class);
                    LOGGER.info("========================收到音频消息：" + voiceMessage);
                } else if (MsgType.LINK.equals(msgType)) {
                    LinkMessage linkMessage = JSONObject.parseObject(jsonString, LinkMessage.class);
                    LOGGER.info("========================收到链接消息：" + linkMessage);
                } else if (MsgType.LOCATION.equals(msgType)) {
                    LocationMessage locationMessage = JSONObject.parseObject(jsonString, LocationMessage.class);
                    LOGGER.info("========================收到位置消息：" + locationMessage);
                } else if (MsgType.VIDEO.equals(msgType) || MsgType.SHORT_VIDEO.equals(msgType)) {
                    VideoMessage videoMessage = JSONObject.parseObject(jsonString, VideoMessage.class);
                    LOGGER.info("========================收到视频消息：" + videoMessage);
                } else if (MsgType.EVENT.equals(msgType)) {
                    LOGGER.info("========================收到事件消息");
                    Event event = JSONObject.parseObject(jsonString, Event.class);
                    result = eventHandleService.handleEvent(request, event);
                }
                //设置正确的 content-type 以防止中文乱码
                response.setContentType("text/xml;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                System.out.println(result);
                writer.write(result);
                writer.close();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * JS-SDK config
     */
    @GetMapping(value = "JsSdkConfig")
    public String JsSdkConfig(String url, HttpServletRequest req) {
        //获取签名
        Signature signature =
                WxWebApis.getSignature(weixinConfigPorperties.getAppid(),
                        getAccessToken(weixinConfigPorperties.getAppid(), weixinConfigPorperties.getSecret()),
                        req.getScheme() + "://" + req.getServerName() + url);
        LOGGER.info("========================进行js签名 - url：" + req.getScheme() + "://" + req.getServerName() + url);
        LOGGER.info("========================进行js签名：" + JSONObject.toJSONString(signature));
        String config = "wx.config('{'\n" +
                "    debug: false, \n" +// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                "    appId: \"{0}\", \n" +// 必填，公众号的唯一标识
                "    timestamp:{1} , \n" +// 必填，生成签名的时间戳
                "    nonceStr: \"{2}\", \n" +// 必填，生成签名的随机串
                "    signature: \"{3}\",\n" +// 必填，签名
                "    jsApiList: [\n" +
                "        \"checkJsApi\",\n" +
                "        \"onMenuShareTimeline\",\n" +
                "        \"onMenuShareAppMessage\",\n" +
                "        \"onMenuShareQQ\",\n" +
                "        \"onMenuShareWeibo\",\n" +
                "        \"onMenuShareQZone\",\n" +
                "        \"hideMenuItems\",\n" +
                "        \"showMenuItems\",\n" +
                "        \"hideAllNonBaseMenuItem\",\n" +
                "        \"showAllNonBaseMenuItem\",\n" +
                "        \"translateVoice\",\n" +
                "        \"startRecord\",\n" +
                "        \"stopRecord\",\n" +
                "        \"onVoiceRecordEnd\",\n" +
                "        \"playVoice\",\n" +
                "        \"onVoicePlayEnd\",\n" +
                "        \"pauseVoice\",\n" +
                "        \"stopVoice\",\n" +
                "        \"uploadVoice\",\n" +
                "        \"downloadVoice\",\n" +
                "        \"chooseImage\",\n" +
                "        \"previewImage\",\n" +
                "        \"uploadImage\",\n" +
                "        \"downloadImage\",\n" +
                "        \"getNetworkType\",\n" +
                "        \"openLocation\",\n" +
                "        \"getLocation\",\n" +
                "        \"hideOptionMenu\",\n" +
                "        \"showOptionMenu\",\n" +
                "        \"closeWindow\",\n" +
                "        \"scanQRCode\",\n" +
                "        \"chooseWXPay\",\n" +
                "        \"openProductSpecificView\",\n" +
                "        \"addCard\",\n" +
                "        \"chooseCard\",\n" +
                "        \"openCard\"]  \n" +//必填，需要使用的JS接口列表
                "'}');";
        return MessageFormat.format(config,
                signature.getAppid(),
                signature.getTimestamp(),
                signature.getNoncestr(),
                signature.getSignature()
        );
    }
}
