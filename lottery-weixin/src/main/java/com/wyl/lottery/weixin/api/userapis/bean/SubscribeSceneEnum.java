package com.wyl.lottery.weixin.api.userapis.bean;

/**
 * The enum Subscribe scene enum.
 *
 * @author Tony
 * @date 2018 -10-26 17:50:20
 */
public enum SubscribeSceneEnum {
    /**
     * Add scene search subscribe scene enum.
     */
    ADD_SCENE_SEARCH("ADD_SCENE_SEARCH", "公众号搜索"),
    /**
     * Add scene account migration subscribe scene enum.
     */
    ADD_SCENE_ACCOUNT_MIGRATION("ADD_SCENE_ACCOUNT_MIGRATION", "公众号迁移"),
    /**
     * Add scene profile card subscribe scene enum.
     */
    ADD_SCENE_PROFILE_CARD("ADD_SCENE_PROFILE_CARD", "名片分享"),
    /**
     * Add scene qr code subscribe scene enum.
     */
    ADD_SCENE_QR_CODE("ADD_SCENE_QR_CODE", "扫描二维码"),
    /**
     * Add sceneprofile link subscribe scene enum.
     */
    ADD_SCENEPROFILE_LINK("ADD_SCENEPROFILE_LINK", "图文页内名称点击"),
    /**
     * Add scene profile item subscribe scene enum.
     */
    ADD_SCENE_PROFILE_ITEM("ADD_SCENE_PROFILE_ITEM", "图文页右上角菜单"),
    /**
     * Add scene paid subscribe scene enum.
     */
    ADD_SCENE_PAID("ADD_SCENE_PAID", "支付后关注"),
    /**
     * Add scene others subscribe scene enum.
     */
    ADD_SCENE_OTHERS("ADD_SCENE_OTHERS", "其他");

    /**
     * The Scene.
     */
    public final String scene;
    /**
     * The Name.
     */
    public final String name;

    /**
     * Instantiates a new Subscribe scene enum.
     *
     * @param scene
     *         the scene
     * @param name
     *         the name
     */
    SubscribeSceneEnum(String scene,String name){
        this.scene = scene;
        this.name = name;
    }
}
