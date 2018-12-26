package com.wyl.lottery.weixin.client.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Account client.
 *
 * @author Tony
 * @date 2018 -10-31 18:59:00
 */
public interface AccountClient {
    /**
     * Oauth ksd result.
     *
     * @param code
     *         the code
     *
     * @return the ksd result
     *
     * @author Tony
     * @date 2018 -10-31 18:59:00
     */
    String oauth(@RequestParam("code") String code);

}
