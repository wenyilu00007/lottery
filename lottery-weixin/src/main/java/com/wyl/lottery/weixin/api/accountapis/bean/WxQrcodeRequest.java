package com.wyl.lottery.weixin.api.accountapis.bean;

import lombok.Data;

import java.util.HashMap;

/**
 * The type Wx qrcode request.
 *
 * @author Tony
 * @date 2018 -10-30 10:35:42
 */
@Data
public class WxQrcodeRequest {
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    private Integer expire_seconds = 2592000;
    /**
     * 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    private String action_name;
    /**
     * 二维码详细信息
     */
    private ActionInfo action_info = new ActionInfo();

    /**
     * Set action info.
     *
     * @param sceneStr
     *         the scene str
     *
     * @author Tony
     * @date 2018 -10-30 10:39:51
     */
    public void setActionInfo(String sceneStr) {
        this.action_info.setSceneStr(sceneStr);
    }

    /**
     * Set action info.
     *
     * @param sceneId
     *         the scene id
     *
     * @author Tony
     * @date 2018 -10-30 10:39:51
     */
    public void setActionInfo(Integer sceneId) {
        this.action_info.setSceneId(sceneId);
    }

    /**
     * The type Action info.
     *
     * @author Tony
     * @date 2018 -10-30 10:53:39
     */
    @Data
    class ActionInfo {
        /**
         * The Scene.
         */
        private Scene scene = new Scene();

        /**
         * Set scene str.
         *
         * @param sceneStr
         *         the scene str
         *
         * @author Tony
         * @date 2018 -10-30 10:53:40
         */
        public void setSceneStr(String sceneStr){
            this.scene.put(Scene.SCENE_STR, sceneStr);
        }

        /**
         * Set scene id.
         *
         * @param sceneId
         *         the scene id
         *
         * @author Tony
         * @date 2018 -10-30 10:53:40
         */
        public void setSceneId(Integer sceneId){
            this.scene.put(Scene.SCENE_ID, sceneId);
        }
    }

    /**
     * The type Scene.
     *
     * @author Tony
     * @date 2018 -10-30 10:35:42
     */
    class Scene extends HashMap<String,Object> {
        /**
         * The Scene str.
         */
        static final String SCENE_STR = "scene_str";
        /**
         * The Scene id.
         */
        static final String SCENE_ID = "scene_id";
    }
}

