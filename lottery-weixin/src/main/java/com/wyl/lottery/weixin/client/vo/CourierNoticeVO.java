package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/11/1
 */
@ApiModel(description = "区域管理vo")
public class CourierNoticeVO implements Serializable {

    private static final long serialVersionUID = 5186575993156863164L;

    @ApiModelProperty(value = "员工id")
    private Long courierid;

    public Long getCourierid() {
        return courierid;
    }

    public void setCourierid(Long courierid) {
        this.courierid = courierid;
    }
}
