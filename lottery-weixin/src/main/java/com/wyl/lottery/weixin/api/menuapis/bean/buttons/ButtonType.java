package com.wyl.lottery.weixin.api.menuapis.bean.buttons;

import java.util.ArrayList;
import java.util.List;

/**
 * 按钮类型
 *
 * @author Tony
 * @date 2018 -10-26 15:29:20
 */
public class ButtonType {
    /**
     * 点击
     */
    public static final String CLICK = "click";
    /**
     * 页面
     */
    public static final String VIEW = "view";
    /**
     * The constant SCANCODE_PUSH.
     */
    public static final String SCANCODE_PUSH = "scancode_push";
    /**
     * The constant SCANCODE_WAITMSG.
     */
    public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
    /**
     * The constant PIC_SYSPHOTO.
     */
    public static final String PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * The constant PIC_PHOTO_OR_ALBUM.
     */
    public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * The constant PIC_WEIXIN.
     */
    public static final String PIC_WEIXIN = "pic_weixin";
    /**
     * The constant LOCATION_SELECT.
     */
    public static final String LOCATION_SELECT = "location_select";
    /**
     * The constant MEDIA_ID.
     */
    public static final String MEDIA_ID = "media_id";
    /**
     * The constant VIEW_LIMITED.
     */
    public static final String VIEW_LIMITED = "view_limited";
    /**
     * The constant MINIPROGRAM.
     */
    public static final String MINIPROGRAM = "miniprogram";

    /**
     * The constant CLICK_GROUP.
     */
    public static final List<String> CLICK_GROUP ;
    /**
     * The constant VIEW_GROUP.
     */
    public static final List<String> VIEW_GROUP;
    /**
     * The constant MEDIA_GROUP.
     */
    public static final List<String> MEDIA_GROUP;
    /**
     * The constant MINIPROGRAM_GROUP.
     */
    public static final List<String> MINIPROGRAM_GROUP;
    static {
        CLICK_GROUP= new ArrayList<>();
        VIEW_GROUP= new ArrayList<>();
        MEDIA_GROUP= new ArrayList<>();
        MINIPROGRAM_GROUP= new ArrayList<>();

        CLICK_GROUP.add(CLICK);
        CLICK_GROUP.add(SCANCODE_PUSH);
        CLICK_GROUP.add(SCANCODE_WAITMSG);
        CLICK_GROUP.add(PIC_PHOTO_OR_ALBUM);
        CLICK_GROUP.add(PIC_SYSPHOTO);
        CLICK_GROUP.add(PIC_WEIXIN);
        CLICK_GROUP.add(LOCATION_SELECT);

        VIEW_GROUP.add(VIEW);

        MEDIA_GROUP.add(MEDIA_ID);
        MEDIA_GROUP.add(VIEW_LIMITED);

        MINIPROGRAM_GROUP.add(MINIPROGRAM);
    }
}
