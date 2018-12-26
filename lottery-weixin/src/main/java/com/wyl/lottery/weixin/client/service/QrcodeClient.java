package com.wyl.lottery.weixin.client.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

/**
 * The interface Qrcode client.
 *
 * @author Tony
 * @date 2018 -10-31 18:55:43
 */
public interface QrcodeClient {
    /**
     * Courier qrcode string.
     *
     * @param companyid
     *         the companyid
     *
     * @return the string
     *
     * @author Tony
     * @date 2018 -10-31 18:55:43
     */
    String courierQrcode(@NotBlank @RequestParam("companyid") Long companyid);

    /**
     * Staff qrcode string.
     *
     * @param companyid
     *         the companyid
     *
     * @return the string
     *
     * @author Tony
     * @date 2018 -10-31 18:55:43
     */
    String staffQrcode(@NotBlank @RequestParam("companyid") Long companyid);
}
