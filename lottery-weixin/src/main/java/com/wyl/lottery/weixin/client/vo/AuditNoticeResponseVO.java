package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "审核通知返回BO")
@Data
public class AuditNoticeResponseVO {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("用户姓名")
    private String username;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像")
    private String headimg;
    @ApiModelProperty("对应用户ID")
    private Long userid;

}
