package com.wyl.lottery.weixin.api.userapis.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Wx user response.
 *
 * @author Tony
 * @date 2018 -10-26 17:08:11
 */
@Data
public class WxUserList implements Serializable {
    /**
     * The Total.
     */
    private Integer total;
    /**
     * The Count.
     */
    private Integer count;
    /**
     * The Data.
     */
    private WxUserData data;
    /**
     * The Next openid.
     */
    private String next_openid;
}
