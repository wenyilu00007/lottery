package com.wyl.lotterytask.gecco.bean.baidu;

import com.wyl.lotterytask.gecco.core.annotation.HtmlField;
import com.wyl.lotterytask.gecco.core.spider.HrefBean;
import com.wyl.lotterytask.gecco.core.spider.HtmlBean;

import java.util.List;

public class NewsLines implements HtmlBean {

    private static final long serialVersionUID = 3018760488621382659L;

    @HtmlField(cssPath = "strong  a")
    private List<HrefBean> newslines;
    @HtmlField(cssPath = "a")
    private List<HrefBean> othernewslines;

    public List<HrefBean> getNewslines() {
        return newslines;
    }

    public void setNewslines(List<HrefBean> newslines) {
        this.newslines = newslines;
    }

    public List<HrefBean> getOthernewslines() {
        return othernewslines;
    }

    public void setOthernewslines(List<HrefBean> othernewslines) {
        this.othernewslines = othernewslines;
    }
}
