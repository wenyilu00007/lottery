package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: 用户信息
 * @Auther: wang.xu
 * @Date: 2018-11-01 2018/11/1:10:44
 */
@ApiModel(description = "用户信息")
public class UserVO implements Serializable{

    private static final long serialVersionUID = 1775399469509770386L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 状态名称
     */
    @ApiModelProperty(value = "状态名称")
    private String statusname;
    /**
     * 呢称
     */
    @ApiModelProperty(value = "呢称")
    private String nickname;
    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyname;
    /**
     * 是否管理员(true-是,false-否)
     */
    @ApiModelProperty(value = "是否管理员(true-是,false-否)")
    private Boolean adminflag;
    /**
     * 当前登录用户所在公司管理员
     */
    @ApiModelProperty(value = "当前登录用户所在公司管理员")
    private String adminname;
    /**
     * 员工总数
     */
    @ApiModelProperty(value = "员工总数")
    private Integer usernumber;
    /**
     * 每月寄出票数
     */
    @ApiModelProperty(value = "每月寄出票数")
    private Integer sendnumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Boolean getAdminflag() {
        return adminflag;
    }

    public void setAdminflag(Boolean adminflag) {
        this.adminflag = adminflag;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public Integer getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(Integer usernumber) {
        this.usernumber = usernumber;
    }

    public Integer getSendnumber() {
        return sendnumber;
    }

    public void setSendnumber(Integer sendnumber) {
        this.sendnumber = sendnumber;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", statusname='" + statusname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", companyname='" + companyname + '\'' +
                ", adminflag=" + adminflag +
                ", adminname='" + adminname + '\'' +
                ", usernumber=" + usernumber +
                ", sendnumber=" + sendnumber +
                '}';
    }
}
