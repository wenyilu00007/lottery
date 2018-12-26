package com.wyl.lottery.weixin.api.userapis.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The type Wx user data.
 *
 * @author Tony
 * @date 2018 -10-26 17:08:07
 */
@Data
public class WxUserData implements Serializable {
    /**
     * The Openid.
     */
    List<String> openid;
}
