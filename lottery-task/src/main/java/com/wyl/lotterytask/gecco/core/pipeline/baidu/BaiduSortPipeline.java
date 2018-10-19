package com.wyl.lotterytask.gecco.core.pipeline.baidu;

import com.google.common.collect.Lists;
import com.wyl.lotterycommon.domain.News;
import com.wyl.lotterycommon.service.NewsService;
import com.wyl.lotterytask.gecco.bean.baidu.NewsLines;
import com.wyl.lotterytask.gecco.bean.baidu.NewsSort;
import com.wyl.lotterytask.gecco.core.annotation.PipelineName;
import com.wyl.lotterytask.gecco.core.pipeline.Pipeline;
import com.wyl.lotterytask.gecco.core.spider.HrefBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@PipelineName("baiduSortPipeline")
public class BaiduSortPipeline implements Pipeline<NewsSort> {

    @Autowired
    private NewsService newsService;
    @Override
    public void process(NewsSort newsSort) {
        List<HrefBean> saveNews = Lists.newArrayList();
        List<NewsLines> allNews = Lists.newArrayList();
        allNews.addAll(newsSort.getHotnews());
        allNews.addAll(newsSort.getSecondOthernews());
        allNews.addAll(newsSort.getThirdOthernews());
        allNews.addAll(newsSort.getFourOthernews());
        allNews.addAll(newsSort.getFiveOthernews());
        allNews.addAll(newsSort.getSexOthernews());
        allNews.forEach(i->{
            List<HrefBean> newslines = i.getNewslines();
            List<HrefBean> othernewslines = i.getOthernewslines();
            if(!CollectionUtils.isEmpty(newslines)){
                saveNews.addAll(newslines);
            }
            if(!CollectionUtils.isEmpty(othernewslines)){
                saveNews.addAll(othernewslines);
            }
        });
        List<News> newsList = saveNews.stream().map(i -> {
            News news = new News();
            news.setSource("百度");
            news.setTitle(i.getTitle());
            news.setUrl(i.getUrl());
            return news;
        }).collect(Collectors.toList());
        newsService.saveNews(newsList);
    }

}






