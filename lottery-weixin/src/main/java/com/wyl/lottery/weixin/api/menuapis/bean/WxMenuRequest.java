package com.wyl.lottery.weixin.api.menuapis.bean;

import lombok.Data;

import java.util.List;

/**
 * The type Wx menu request.
 *
 * @author Tony
 * @date 2018 -11-01 18:56:06
 */
@Data
public class WxMenuRequest {
    /**
     * The Button.
     */
    List<Object> button;
}
