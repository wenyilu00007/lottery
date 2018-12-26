package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 位置消息
 *
 * @author Tony
 * @date 2018 -11-01 16:26:36
 */
@Data
@XStreamAlias("xml")
public class LocationMessage extends BaseMessage{
    /**
     * The Location x.
     */
    @XStreamAlias("Location_X")
    private String locationX;
    /**
     * The Location y.
     */
    @XStreamAlias("Location_Y")
    private String locationY;
    /**
     * The Scale.
     */
    @XStreamAlias("Scale")
    private Integer scale;
    /**
     * The Label.
     */
    @XStreamAlias("Label")
    private String label;
    /**
     * The Msg id.
     */
    @XStreamAlias("MsgId")
    private Long msgId;
}
