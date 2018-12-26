package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "通知审核BO")
@Data
public class NoticeAuditRequestVO {

    @ApiModelProperty(value = "部门人员ID，必填")
    private Long deptId;
    @ApiModelProperty(value = "操作类型（1：审核，2：拒绝 ），必填")
    private Integer auditType;

}
