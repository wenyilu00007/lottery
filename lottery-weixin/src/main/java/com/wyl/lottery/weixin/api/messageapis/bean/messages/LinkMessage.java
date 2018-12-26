package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 链接消息
 *
 * @author Tony
 * @date 2018 -11-01 16:26:33
 */
@Data
@XStreamAlias("xml")
public class LinkMessage extends BaseMessage{
    /**
     * The Title.
     */
    @XStreamAlias("Title")
    private String title;
    /**
     * The Description.
     */
    @XStreamAlias("Description")
    private String description;
    /**
     * The Url.
     */
    @XStreamAlias("Url")
    private String url;
    /**
     * The Msg id.
     */
    @XStreamAlias("MsgId")
    private Long msgId;
}
