package com.wyl.lottery.weixin.api.menuapis.bean.buttons;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 基础按钮.
 *
 * @author Tony
 * @date 2018 -10-26 14:58:53
 */
@Data
public class BaseButton implements Serializable {
    /**
     * 按钮名称
     */
    protected String name;

    /**
     * 二级菜单
     */
    protected List<Object> sub_button;

    /**
     * Instantiates a new Base button.
     */
    public BaseButton() {
    }

    /**
     * Instantiates a new Base button.
     *
     * @param name
     *         the name
     */
    public BaseButton(String name) {
        this.name = name;
    }
}
