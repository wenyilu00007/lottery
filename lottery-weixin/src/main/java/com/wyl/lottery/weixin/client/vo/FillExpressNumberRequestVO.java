package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "填入单号VO")
@Data
public class FillExpressNumberRequestVO {

    @ApiModelProperty(value = "订单id，必填")
    private Long ordersId;
    @ApiModelProperty(value = "运单号")
    private String expressNumber;

}
