package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 文本消息
 *
 * @author Tony
 * @date 2018 -11-01 16:26:40
 */
@Data
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    /**
     * The Content.
     */
    @XStreamAlias("Content")
    private String content;
    /**
     * The Msg id.
     */
    @XStreamAlias("MsgId")
    private Long msgId;

}
