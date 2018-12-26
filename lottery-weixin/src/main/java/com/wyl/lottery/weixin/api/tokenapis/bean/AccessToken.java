package com.wyl.lottery.weixin.api.tokenapis.bean;

import lombok.Data;

/**
 * The type Access token success.
 *
 * @author Tony
 * @date 2018 -10-26 13:49:29
 */
@Data
public class AccessToken {
    /**
     * The Access token.
     */
    private String access_token;
    /**
     * The Expires in.
     */
    private Integer expires_in;
}
