package com.wyl.lottery.weixin.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: 供应商信息
 * @Auther: wang.xu
 * @Date: 2018-11-01 2018/11/1:10:20
 */
@ApiModel(description = "供应商信息")
public class SupplierVO implements Serializable{

    private static final long serialVersionUID = -5724682123019743767L;
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 左边图标
     */
    @ApiModelProperty(value = "左边图标")
    private String iconurl;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String introduction;

    /**
     * 运力品牌图标
     */
    @ApiModelProperty(value = "运力品牌图标")
    private String brandinconurl;

    /**
     * 是否显示服务类型标识
     */
    @ApiModelProperty(value = "是否显示服务类型标识",notes = "true:显示，false:不显示")
    private boolean showservicetypeflag;
    @ApiModelProperty(value="供应商_时效类型_服务类型",notes = "拼接字符串")
    private List<Map<String,Object>> servicetypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBrandinconurl() {
        return brandinconurl;
    }

    public void setBrandinconurl(String brandinconurl) {
        this.brandinconurl = brandinconurl;
    }

    public boolean isShowservicetypeflag() {
        return showservicetypeflag;
    }

    public void setShowservicetypeflag(boolean showservicetypeflag) {
        this.showservicetypeflag = showservicetypeflag;
    }

    public List<Map<String, Object>> getServicetypes() {
        return servicetypes;
    }

    public void setServicetypes(List<Map<String, Object>> servicetypes) {
        this.servicetypes = servicetypes;
    }

    @Override
    public String toString() {
        return "{\"SupplierVO\":{"
                + "                        \"id\":\"" + id + "\""
                + ",                         \"name\":\"" + name + "\""
                + ",                         \"iconurl\":\"" + iconurl + "\""
                + ",                         \"introduction\":\"" + introduction + "\""
                + ",                         \"brandinconurl\":\"" + brandinconurl + "\""
                + ",                         \"showservicetypeflag\":\"" + showservicetypeflag + "\""
                + ",                         \"servicetypes\":" + servicetypes
                + "}}";
    }
}
