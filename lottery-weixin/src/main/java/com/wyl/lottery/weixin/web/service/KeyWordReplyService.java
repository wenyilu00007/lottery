package com.wyl.lottery.weixin.web.service;


import com.wyl.lottery.weixin.api.messageapis.bean.messages.TextMessage;

import javax.servlet.http.HttpServletRequest;

public interface KeyWordReplyService {
    String handleKeyWord(HttpServletRequest request, TextMessage textMessage);
}
