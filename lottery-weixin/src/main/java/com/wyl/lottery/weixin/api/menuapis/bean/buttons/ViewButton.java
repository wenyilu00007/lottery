package com.wyl.lottery.weixin.api.menuapis.bean.buttons;

import lombok.Data;

/**
 * 跳转页面的菜单.
 *
 * @author Tony
 * @date 2018 -10-26 15:02:56
 */
@Data
public class ViewButton extends BaseButton {
    /**
     * The Type.
     */
    private String type;
    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节
     */
    private String url;

    public ViewButton(String name, String url) {
        this.name = name;
        this.type = ButtonType.VIEW;
        this.url = url;
    }
}
