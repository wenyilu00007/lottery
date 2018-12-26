package com.wyl.lottery.weixin.web.service;


import com.wyl.lottery.weixin.api.messageapis.bean.WxTempMsgRequest;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/11/1
 */
public interface CommonNoticeService {

    /**
     * @Description: 通用的消息推送方法
     * @Author <a href="mailto:wangshuo@ksudi.com">wangshuo</a>
     * @Date 2018/11/1 13:59
     */
    public void noticeMessage(WxTempMsgRequest request);
}
