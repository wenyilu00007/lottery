package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: 通讯录信息
 * @Auther: wang.xu
 * @Date: 2018-11-01 2018/11/1:10:44
 */
@ApiModel(description = "通讯录信息")
public class AddressbookVO implements Serializable{

    private static final long serialVersionUID = -8178386697973194433L;

    /** 主键id*/
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userid;
    /**
     * 城市ID
     */
    @ApiModelProperty(value = "城市ID")
    private Long cityid;
    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    private Long companyid;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String name;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;
    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String address;
    /**
     * 收发件类型（1：收，2：发）
     * @link com.ksudi.oms.client.enums.AddressbookEnum
     */
    @ApiModelProperty(value = "收发件类型（1：收，2：发）")
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getCityid() {
        return cityid;
    }

    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    public Long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AddressbookVO{" +
                "id=" + id +
                ", userid=" + userid +
                ", cityid=" + cityid +
                ", companyid=" + companyid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                '}';
    }
}
