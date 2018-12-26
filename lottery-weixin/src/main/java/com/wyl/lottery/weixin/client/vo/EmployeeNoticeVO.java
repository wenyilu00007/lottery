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
public class EmployeeNoticeVO implements Serializable {

    private static final long serialVersionUID = -2811953861991255396L;

    @ApiModelProperty(value = "员工id")
    private Long employeeid;

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }
}
