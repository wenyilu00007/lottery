package com.wyl.lottery.weixin.api.menuapis.bean.buttons;

import lombok.Data;

/**
 * 小程序的菜单.
 *
 * @author Tony
 * @date 2018 -10-26 15:02:56
 */
@Data
public class MiniProgramButton extends BaseButton{
    /**
     * The Type.
     */
    private String type;
    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url
     */
    private String url;
    /**
     * 小程序的appid（仅认证公众号可配置）
     */
    private String appid;
    /**
     * 小程序的页面路径
     */
    private String pagepath;

    public MiniProgramButton(String name, String url, String appid, String pagepath) {
        this.name = name;
        this.type = ButtonType.MINIPROGRAM;
        this.url = url;
        this.appid = appid;
        this.pagepath = pagepath;
    }
}
