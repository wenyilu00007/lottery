package com.wyl.lottery.weixin.api.messageapis.bean.messages;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * The type Base message.
 *
 * @author Tony
 * @date 2018 -10-27 13:39:23
 */
@Data
public class BaseMessage {
    /**
     * 开发者微信号
     */
    @XStreamAlias("ToUserName")
    protected String toUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    @XStreamAlias("FromUserName")
    protected String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @XStreamAlias("CreateTime")
    protected Long createTime;
    /**
     * The Msg type.
     */
    @XStreamAlias("MsgType")
    protected String msgType;
}
