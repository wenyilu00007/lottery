package com.wyl.lottery.weixin.api.messageapis.bean.messages;

/**
 * 事件类型
 *
 * @author Tony
 * @date 2018 -11-01 14:04:07
 */
public class EventType {
    /**
     * 订阅
     */
    public static final String SUBSCRIBE = "subscribe";
    /**
     * 取消订阅
     */
    public static final String UNSUBSCRIBE = "unsubscribe";
    /**
     * 扫描
     */
    public static final String SCAN = "SCAN";
    /**
     * 位置
     */
    public static final String LOCATION = "LOCATION";
    /**
     * 点击
     */
    public static final String CLICK = "CLICK";
    /**
     * 页面
     */
    public static final String VIEW = "VIEW";
}
