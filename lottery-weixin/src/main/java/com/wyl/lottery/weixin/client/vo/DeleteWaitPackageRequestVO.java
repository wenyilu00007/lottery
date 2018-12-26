package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "删除待揽件通知信息VO")
@Data
public class DeleteWaitPackageRequestVO {

    @ApiModelProperty(value = "公司id，必填")
    private Long companyId;

}
