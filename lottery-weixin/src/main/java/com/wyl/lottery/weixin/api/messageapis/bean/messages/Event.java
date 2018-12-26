package com.wyl.lottery.weixin.api.messageapis.bean.messages;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * 事件
 *
 * @author Tony
 * @date 2018 -11-01 13:58:31
 */
@Data
public class Event extends BaseMessage {
    /**
     * 事件类型
     */
    @XStreamAlias("Event")
    private String event;
    /**
     * 事件KEY值
     */
    @XStreamAlias("EventKey")
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @XStreamAlias("Ticket")
    private String ticket;
    /**
     * 地理位置纬度
     */
    @XStreamAlias("Latitude")
    private String Latitude;
    /**
     * 地理位置经度
     */
    @XStreamAlias("Longitude")
    private String Longitude;
    /**
     * 地理位置精度
     */
    @XStreamAlias("Precision")
    private String Precision;
}
