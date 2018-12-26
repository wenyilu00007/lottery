package com.wyl.lottery.weixin.api.webapis.bean;

import com.wyl.lottery.weixin.api.base.WxResponse;
import lombok.Data;

/**
 * The type Wx jsapi response.
 *
 * @author Tony
 * @date 2018 -10-29 09:22:44
 */
@Data
public class WxJsapiResponse extends WxResponse {
    /**
     * The Ticket.
     */
    private String ticket;
    /**
     * The Expires in.
     */
    private int expires_in;
}
