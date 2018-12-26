package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "审核通知BO")
@Data
public class AuditNoticeRequestVO {

    @ApiModelProperty(value = "用户id，必填")
    private Long userId;

    /**
     * 姓名
     */
    private String name;
    /**
     * 申请时间
     */
    private Long applyTime;
    /**
     * 跳转路径
     */
    private String url;

}
