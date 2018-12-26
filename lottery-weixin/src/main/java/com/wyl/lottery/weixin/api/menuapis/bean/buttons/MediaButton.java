package com.wyl.lottery.weixin.api.menuapis.bean.buttons;

import lombok.Data;

/**
 * 永久素材的菜单.
 *
 * @author Tony
 * @date 2018 -10-26 15:02:56
 */
@Data
public class MediaButton extends BaseButton{
    /**
     * The Type.
     */
    private String type;
    /**
     * 调用新增永久素材接口返回的合法media_id
     */
    private String media_id;

    public MediaButton(String name, String type, String media_id) {
        this.name = name;
        this.type = type;
        this.media_id = media_id;
    }
}
