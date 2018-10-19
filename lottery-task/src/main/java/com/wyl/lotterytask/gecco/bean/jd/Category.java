package com.wyl.lotterytask.gecco.bean.jd;

import com.wyl.lotterytask.gecco.core.annotation.HtmlField;
import com.wyl.lotterytask.gecco.core.annotation.Text;
import com.wyl.lotterytask.gecco.core.spider.HrefBean;
import com.wyl.lotterytask.gecco.core.spider.HtmlBean;

import java.util.List;

public class Category implements HtmlBean {

    private static final long serialVersionUID = 3018760488621382659L;

    @Text
    @HtmlField(cssPath="dt a")
    private String parentName;

    @HtmlField(cssPath="dd a")
    private List<HrefBean> categorys;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<HrefBean> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<HrefBean> categorys) {
        this.categorys = categorys;
    }

}
