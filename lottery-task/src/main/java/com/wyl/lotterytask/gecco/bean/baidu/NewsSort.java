package com.wyl.lotterytask.gecco.bean.baidu;

import com.wyl.lotterytask.gecco.core.annotation.Gecco;
import com.wyl.lotterytask.gecco.core.annotation.HtmlField;
import com.wyl.lotterytask.gecco.core.annotation.Request;
import com.wyl.lotterytask.gecco.core.request.HttpRequest;
import com.wyl.lotterytask.gecco.core.spider.HtmlBean;

import java.util.List;

@Gecco(matchUrl = "http://news.baidu.com", pipelines = {"baiduSortPipeline"})
public class NewsSort implements HtmlBean {

    private static final long serialVersionUID = -3770871271092767593L;

    @Request
    private HttpRequest request;

    @HtmlField(cssPath = "#pane-news > div > ul > li")
    private List<NewsLines> hotnews;
    @HtmlField(cssPath = "#pane-news > ul:nth-child(1) > li")
    private List<NewsLines> firstOthernews;
    @HtmlField(cssPath = "#pane-news > ul:nth-child(2) > li")
    private List<NewsLines> secondOthernews;
    @HtmlField(cssPath = "#pane-news > ul:nth-child(3) > li")
    private List<NewsLines> thirdOthernews;
    @HtmlField(cssPath = "#pane-news > ul:nth-child(4) > li")
    private List<NewsLines> fourOthernews;
    @HtmlField(cssPath = "#pane-news > ul:nth-child(5) > li")
    private List<NewsLines> fiveOthernews;
    @HtmlField(cssPath = "#pane-news > ul:nth-child(6) > li")
    private List<NewsLines> sexOthernews;

    public List<NewsLines> getHotnews() {
        return hotnews;
    }

    public void setHotnews(List<NewsLines> hotnews) {
        this.hotnews = hotnews;
    }

    public List<NewsLines> getFirstOthernews() {
        return firstOthernews;
    }

    public void setFirstOthernews(List<NewsLines> firstOthernews) {
        this.firstOthernews = firstOthernews;
    }

    public List<NewsLines> getSecondOthernews() {
        return secondOthernews;
    }

    public void setSecondOthernews(List<NewsLines> secondOthernews) {
        this.secondOthernews = secondOthernews;
    }

    public List<NewsLines> getThirdOthernews() {
        return thirdOthernews;
    }

    public void setThirdOthernews(List<NewsLines> thirdOthernews) {
        this.thirdOthernews = thirdOthernews;
    }

    public List<NewsLines> getFourOthernews() {
        return fourOthernews;
    }

    public void setFourOthernews(List<NewsLines> fourOthernews) {
        this.fourOthernews = fourOthernews;
    }

    public List<NewsLines> getFiveOthernews() {
        return fiveOthernews;
    }

    public void setFiveOthernews(List<NewsLines> fiveOthernews) {
        this.fiveOthernews = fiveOthernews;
    }

    public List<NewsLines> getSexOthernews() {
        return sexOthernews;
    }

    public void setSexOthernews(List<NewsLines> sexOthernews) {
        this.sexOthernews = sexOthernews;
    }
}
