package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 音频消息
 *
 * @author Tony
 * @date 2018 -11-01 16:26:46
 */
@Data
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{
    /**
     * The Format.
     */
    @XStreamAlias("Format")
    private String format;
    /**
     * The Media id.
     */
    @XStreamAlias("MediaId")
    private String mediaId;
    /**
     * The Msg id.
     */
    @XStreamAlias("MsgId")
    private Long msgId;
    /**
     * The Recognition.
     */
    @XStreamAlias("Recognition")
    private String recognition;
}
