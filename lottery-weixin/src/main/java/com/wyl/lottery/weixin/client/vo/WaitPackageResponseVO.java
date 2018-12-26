package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "待揽收列表返回BO")
@Data
public class WaitPackageResponseVO implements Serializable {

    /**
     * 等待揽收包裹数量
     */
    @ApiModelProperty(value = "待揽收包裹数量")
    private Integer expressnum;

    @ApiModelProperty(value = "公司id")
    private Long companyid;

    @ApiModelProperty(value = "公司名称")
    private String companyname;

}
