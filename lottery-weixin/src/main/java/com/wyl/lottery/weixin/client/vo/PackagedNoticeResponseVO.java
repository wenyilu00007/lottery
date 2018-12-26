package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "已揽收通知（快递寄出）VO")
@Data
public class PackagedNoticeResponseVO {

    @ApiModelProperty("供应商logo")
    private String logourl;

    @ApiModelProperty("快递单号")
    private String expressnumber;

    @ApiModelProperty("跟踪记录数组")
    private List<PackageNoticeDetailVO> list;

    /**
     * 发件人
     */
    @ApiModelProperty("发件人")
    private String sender;
    /**
     * 发件电话
     */
    @ApiModelProperty("发件电话")
    private String sendphone;
    /**
     * 发件地址
     */
    @ApiModelProperty("发件地址")
    private String sendaddress;

    /**
     * 收件人
     */
    @ApiModelProperty("收件人")
    private String receiver;
    /**
     * 收件电话
     */
    @ApiModelProperty("收件电话")
    private String receivephone;
    /**
     * 收件地址
     */
    @ApiModelProperty("收件地址")
    private String receiveaddress;

    /**
     * 打印次数
     */
    @ApiModelProperty("打印次数")
    private Integer printcount;

}
