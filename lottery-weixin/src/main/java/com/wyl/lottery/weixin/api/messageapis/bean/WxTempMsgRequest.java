package com.wyl.lottery.weixin.api.messageapis.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Wx temp msg request.
 *
 * @author Tony
 * @date 2018 -10-29 18:02:19
 */
@Data
public class WxTempMsgRequest {
    /**
     * 接收者openid，必填
     */
    private String touser;
    /**
     * 模板ID，必填
     */
    private String template_id;
    /**
     * 模板跳转链接，必填
     */
    private String url;
    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private MiniProgram miniprogram;
    /**
     * 模板数据，必填
     */
    private Map<String,Object> data = new HashMap<>();

    /**
     * Add data.
     *
     * @param name
     *         the name
     * @param value
     *         the value
     * @param color
     *         the color 模板内容字体颜色，不填默认为黑色
     *
     * @author Tony
     * @date 2018 -10-29 20:04:27
     */
    public void addData(String name, Object value ,String color){
        Map<String,Object> field = new HashMap<>(1);
        field.put("value",value);
        field.put("color",color);
        data.put(name,field);
    }

    /**
     * Add data.
     *
     * @param name
     *         the name
     * @param value
     *         the value
     *
     * @author Tony
     * @date 2018 -10-29 20:04:27
     */
    public void addData(String name, Object value){
        Map<String,Object> field = new HashMap<>(1);
        field.put("value",value);
        data.put(name,field);
    }

    /**
     * The type Mini program.
     *
     * @author Tony
     * @date 2018 -10-29 20:04:27
     */
    @Data
    class MiniProgram{
        /**
         * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏），必填
         */
        private String appid;
        /**
         * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
         */
        private String pagepath;
    }
}
