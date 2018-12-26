package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 图片消息
 *
 * @author Tony
 * @date 2018 -11-01 16:26:30
 */
@Data
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{
    /**
     * The Pic url.
     */
    @XStreamAlias("PicUrl")
    private String picUrl;
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
