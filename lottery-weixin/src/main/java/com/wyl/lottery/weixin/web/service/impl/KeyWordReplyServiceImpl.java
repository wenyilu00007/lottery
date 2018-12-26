package com.wyl.lottery.weixin.web.service.impl;

import com.thoughtworks.xstream.XStream;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.MsgType;
import com.wyl.lottery.weixin.api.messageapis.bean.messages.TextMessage;
import com.wyl.lottery.weixin.web.service.KeyWordReplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class KeyWordReplyServiceImpl implements KeyWordReplyService {
    private static final String KEY_WORD_1 = "黑科技";
    private static final String REPLY_1 = "欢迎使用快速递企业寄件管理系统\n" +
            "免费注册入口：http://yun.ksudi.com";

    @Override
    public String handleKeyWord(HttpServletRequest request, TextMessage textMessage) {
        if(StringUtils.isNotEmpty(textMessage.getContent()) && textMessage.getContent().contains(KEY_WORD_1)){
            TextMessage replyMessage = new TextMessage();
            replyMessage.setMsgType(MsgType.TEXT);
            replyMessage.setCreateTime(System.currentTimeMillis());
            replyMessage.setFromUserName(textMessage.getToUserName());
            replyMessage.setToUserName(textMessage.getFromUserName());
            replyMessage.setContent(REPLY_1);
            XStream xStream = new XStream();
            //声明XStream注解来源
            xStream.processAnnotations(TextMessage.class);
            return xStream.toXML(replyMessage);
        }
        return "";
    }
}
