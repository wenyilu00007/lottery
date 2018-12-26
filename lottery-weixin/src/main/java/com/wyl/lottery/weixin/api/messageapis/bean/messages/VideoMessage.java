package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 视频消息
 *
 * @author Tony
 * @date 2018 -11-01 16:26:43
 */
@Data
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage{
    /**
     * The Thumb media id.
     */
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;
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
}
