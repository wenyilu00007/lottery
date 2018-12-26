package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "待揽收通知BO")
@Data
public class WaitPackageNoticeRequestVO {

    @ApiModelProperty(value = "用户id，必填")
    private Long userId;
    @ApiModelProperty(value = "企业id")
    private String companyId;

    /**
     * 等待揽收包裹数量
     */
    private Integer expressNum;
    /**
     * 公司名
     */
    private String companyName;
    /**
     * 企业电话
     */
    private Integer companyTel;
    /**
     * 跳转路径
     */
    private String url;

}
