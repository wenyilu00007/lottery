package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "待揽收通知返回BO")
@Data
public class WaitPackageNoticeResponseVO {

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long id;
    /**
     * 发件人
     */
    @ApiModelProperty(value = "发件人")
    private String sender;
    /**
     * 发件电话
     */
    @ApiModelProperty(value = "发件电话")
    private String sendphone;
    /**
     * 发件地址
     */
    @ApiModelProperty(value = "发件地址")
    private String sendaddress;

    /**
     * 收件人
     */
    @ApiModelProperty(value = "收件人")
    private String receiver;
    /**
     * 收件电话
     */
    @ApiModelProperty(value = "收件电话")
    private String receivephone;
    /**
     * 收件地址
     */
    @ApiModelProperty(value = "收件地址")
    private String receiveaddress;
    /**
     * 物品类别
     */
    @ApiModelProperty(value = "物品类别")
    private String category;
    /**
     * 重量(单位千克)[接收页面参数单位千克]
     */
    @ApiModelProperty(value = "重量(单位千克)[接收页面参数单位千克]")
    private Double doubleweight;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
