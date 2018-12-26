package com.wyl.lottery.weixin.client.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeBO {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "快递公司id")
    private Long companyid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }
}
