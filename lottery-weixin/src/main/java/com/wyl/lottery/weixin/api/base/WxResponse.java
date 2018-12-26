package com.wyl.lottery.weixin.api.base;

import lombok.Data;

/**
 * The type Base response.
 *
 * @author Tony
 * @date 2018 -10-26 17:00:01
 */
@Data
public class WxResponse {
    public static final String ERRCODE = "errcode";
    /**
     * The Errcode.
     */
    protected Integer errcode;
    /**
     * The Errmsg.
     */
    protected String errmsg;
}
