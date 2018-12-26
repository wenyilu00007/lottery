package com.wyl.lottery.weixin.web.ms;

import com.wyl.lottery.weixin.api.accountapis.WxAccountApis;
import com.wyl.lottery.weixin.api.accountapis.bean.QrcodeType;
import com.wyl.lottery.weixin.api.accountapis.bean.WxQrcode;
import com.wyl.lottery.weixin.api.accountapis.bean.WxQrcodeRequest;
import com.wyl.lottery.weixin.web.config.WeixinConfigPorperties;
import com.wyl.lottery.weixin.web.controller.AbstractWeixinSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * The type Qrcode micro service.
 *
 * @author Tony
 * @date 2018 -10-31 18:43:19
 */
@Api(value = "二维码", tags = "二维码相关")
@RestController
@RequestMapping("${server.servlet.context-path}")
public class QrcodeMicroService extends AbstractWeixinSupport {
    /**
     * The Weixin config porperties.
     */
    @Autowired
    private WeixinConfigPorperties weixinConfigPorperties;

    /**
     * Courier qrcode string.
     *
     * @param companyid
     *         the companyid
     *
     * @return the string
     *
     * @author Tony
     * @date 2018 -10-31 18:43:19
     */
    @ApiOperation(value = "获取快递员二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyid", value = "公司id", required = true, paramType = "query", dataType = "Long"),
    })
    @Validated
    @GetMapping("ms/qrcode/courierqrcode/v1")
    public String courierQrcode(@NotBlank @RequestParam("companyid")  Long companyid) {
        String redisKey = REDIS_COURIER_QRCODE_PREFIX + companyid;
            WxQrcodeRequest wxQrcodeRequest = new WxQrcodeRequest();
            wxQrcodeRequest.setAction_name(QrcodeType.QR_STR_SCENE);
            wxQrcodeRequest.setActionInfo("COURIER_" + companyid.toString());
            WxQrcode qrcode = WxAccountApis.createQrcode(getAccessToken(weixinConfigPorperties.getAppid(),
                    weixinConfigPorperties.getSecret()), wxQrcodeRequest);
            if (qrcode != null) {
                return qrcode.getTicket();
            }
            return "";
    }

    /**
     * Staff qrcode string.
     *
     * @param companyid
     *         the companyid
     *
     * @return the string
     *
     * @author Tony
     * @date 2018 -10-31 18:43:19
     */
    @ApiOperation(value = "获取员工二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyid", value = "公司id", required = true, paramType = "query", dataType = "Long"),
    })
    @Validated
    @GetMapping("ms/qrcode/staffqrcode/v1")
    public String staffQrcode(@NotBlank @RequestParam("companyid")  Long companyid) {
        WxQrcodeRequest wxQrcodeRequest = new WxQrcodeRequest();
        wxQrcodeRequest.setAction_name(QrcodeType.QR_LIMIT_STR_SCENE);
        wxQrcodeRequest.setActionInfo("COMPANY_" + companyid.toString());
        WxQrcode qrcode = WxAccountApis.createQrcode(getAccessToken(weixinConfigPorperties.getAppid(),
                weixinConfigPorperties.getSecret()), wxQrcodeRequest);
        if (qrcode != null) {
            return qrcode.getTicket();
        }
        return "";
    }
}
