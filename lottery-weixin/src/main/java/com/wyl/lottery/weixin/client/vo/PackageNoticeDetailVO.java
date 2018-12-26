package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel(value = "已揽收通知详情")
@Data
@AllArgsConstructor
public class PackageNoticeDetailVO {
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createtime;
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;
    /**
     * 状态
     */
    @ApiModelProperty("状态com.ksudi.record.client.enums.ExpressRecordStatusEnum}")
    private String status;
}
