package com.wyl.lottery.weixin.web.service.impl;

import com.wyl.lottery.weixin.api.messageapis.WxMessageApis;
import com.wyl.lottery.weixin.api.messageapis.bean.WxTempMsgRequest;
import com.wyl.lottery.weixin.web.config.WeixinConfigPorperties;
import com.wyl.lottery.weixin.web.controller.AbstractWeixinSupport;
import com.wyl.lottery.weixin.web.service.CommonNoticeService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/11/1
 */
@Service
public class CommonNoticeServiceImpl extends AbstractWeixinSupport implements CommonNoticeService {

    private static final Logger logger = LoggerFactory.getLogger(CommonNoticeServiceImpl.class);

    @Autowired
    private WeixinConfigPorperties weixinConfigPorperties;

    private static final String ACCESS_TOKEN = "15_6h5fKBJYz0wDHXtQvOkUBo7eLWeF67W1wxk-dhA0bLy4VSht5G2_AG2u-o8DxxF1h76VULEDevj21cGH_weWzwDjls7Iyl8x8n-FKkRFm1wIwKJkjng2hSQxhiINEThADAJAP";

    @Override
    public void noticeMessage(WxTempMsgRequest request) {
        try {
            if (request == null) {
                logger.error("参数不能为空！");
                throw new Exception("参数不能为空！");
            }
            if (StringUtils.isEmpty(request.getTemplate_id())) {
                logger.error("模板ID不能为空！");
                throw new Exception("模板ID不能为空！");
            }
            if (StringUtils.isEmpty(request.getTouser())) {
                logger.error("接收者OPENID不能为空！");
                throw new Exception("接收者OPENID不能为空！");
            }
            if (MapUtils.isEmpty(request.getData())) {
                logger.error("模板数据不能为空！");
                throw new Exception("模板数据不能为空！");
            }
            WxTempMsgRequest wxTempMsgRequest = new WxTempMsgRequest();
            if (StringUtils.isNotEmpty(request.getUrl())) {
                wxTempMsgRequest.setUrl(request.getUrl());
            }
            wxTempMsgRequest.setTemplate_id(request.getTemplate_id());
            wxTempMsgRequest.setTouser(request.getTouser());
            wxTempMsgRequest.setData(request.getData());
            WxMessageApis.sendTemplateMessage(getAccessToken(weixinConfigPorperties.getAppid(), weixinConfigPorperties.getSecret()), wxTempMsgRequest);
            logger.info("--------推送完成--------token is :" + getAccessToken(weixinConfigPorperties.getAppid(), weixinConfigPorperties.getSecret()));
        } catch (Exception e) {
            logger.error("推送揽收件消息失败！", e.getMessage());
        }
    }

}
