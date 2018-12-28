package com.wyl.lottery.weixin.web.service.impl;

import com.thoughtworks.xstream.XStream;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.Event;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.EventType;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.GraphicMessage;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.MsgType;
import com.wyl.lottery.weixin.web.service.EventHandleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Wx message service.
 *
 * @author Tony
 * @date 2018 -11-01 14:09:24
 */
@Service
public class EventHandleServiceImpl implements EventHandleService {
    @Value("${wyl.weixin.baseurl}")
    private String ssoUrl;
    @Value("${server.servlet.context-path}")
    private String ksudiContextPath;

    @Override
    public String handleEvent(HttpServletRequest request, Event event) {
        // 是否需要回复
        boolean needReply = EventType.SCAN.equals(event.getEvent()) || EventType.SUBSCRIBE.equals(event.getEvent()) && StringUtils.isNotEmpty(event.getTicket());
        if (needReply) {
            String eventKey = event.getEventKey();
            if (StringUtils.isNotEmpty(eventKey)) {
                String[] strs = eventKey.split("_");
                String id = strs[strs.length - 1];
                String prefix = strs[strs.length - 2];
                //构造图文消息
                GraphicMessage graphicMessage = new GraphicMessage();
                graphicMessage.setFromUserName(event.getToUserName());
                graphicMessage.setToUserName(event.getFromUserName());
                graphicMessage.setArticleCount(1);
                graphicMessage.setCreateTime(System.currentTimeMillis());
                graphicMessage.setMsgType(MsgType.NEWS);
                GraphicMessage.GraphicArticle article = graphicMessage.createArticle();
                if ("COMPANY".equals(prefix)) {
                    article.setTitle("快速递企业快递下单");
                    article.setUrl(ssoUrl + "/weixin/employeeCode?openid=" + event.getFromUserName() + "&companyid=" + id);
                    article.setDescription("欢迎使用超级oms系统，点击按钮开始下单");
                    article.setPicUrl(request.getScheme() + "://" + request.getServerName() + ksudiContextPath +
                            "/images/staffbind.png");
                } else {
                    article.setTitle("快速递下单平台");
                    article.setUrl(ssoUrl + "/weixin/courierCode?openid=" + event.getFromUserName() + "&companyid=" + id);
                    article.setDescription("欢迎使用超级OMS系统，点击按钮完善信息");
                    article.setPicUrl(request.getScheme() + "://" + request.getServerName() + ksudiContextPath +
                            "/images/courierbind.png");
                }
                XStream xStream = new XStream();
                //声明XStream注解来源
                xStream.processAnnotations(GraphicMessage.class);
                return xStream.toXML(graphicMessage);
            }
        }
        return "";
    }
}
