package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "已揽收通知（快递寄出）BO")
@Data
public class PackagedNoticeRequestVO {

    @ApiModelProperty(value = "单号id，必填")
    private Long ordersId;

}
