package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "签收通知BO")
@Data
public class SignNoticeRequestVO {

    @ApiModelProperty(value = "用户id，必填")
    private Long userId;
    @ApiModelProperty(value = "快递id，必填")
    private Long ordersId;
    /**
     * 跳转路径
     */
    private String url;
    /**
     * 收件人
     */
    private String receiveName;
    /**
     * 收件电话
     */
    private Integer receiveMobile;
    /**
     * 收件地址
     */
    private String receiveAddress;

}
