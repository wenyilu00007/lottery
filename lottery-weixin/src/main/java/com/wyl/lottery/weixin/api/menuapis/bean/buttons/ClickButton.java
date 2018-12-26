package com.wyl.lottery.weixin.api.menuapis.bean.buttons;

import lombok.Data;

/**
 * 点击类型菜单.
 *
 * @author Tony
 * @date 2018 -10-26 15:00:58
 */
@Data
public class ClickButton extends BaseButton {
    /**
     * The Type.
     */
    private String type;
    /**
     * The Key.
     */
    private String key;

    public ClickButton(String name, String type, String key) {
        this.name = name;
        this.type = type;
        this.key = key;
    }
}
