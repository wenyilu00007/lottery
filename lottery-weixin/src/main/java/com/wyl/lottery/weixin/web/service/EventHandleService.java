package com.wyl.lottery.weixin.web.service;


import com.wyl.lottery.weixin.api.messageapis.bean.messages.Event;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Wx message service.
 *
 * @author Tony
 * @date 2018 -11-01 14:09:38
 */
public interface EventHandleService {
    /**
     * 处理事件
     *
     * @param request
     *         the request
     * @param event
     *         the event
     *
     * @return the string
     *
     * @author Tony
     * @date 2018 -11-07 17:15:08
     */
    String handleEvent(HttpServletRequest request, Event event);
}
