package com.wyl.lotterycommon.service;

import com.wyl.lotterycommon.domain.News;

import java.util.List;

public interface NewsService {

    /**
     * 批量保存新闻
     * @param saveNews
     */
    void saveNews(List<News> saveNews);

    /**
     * 分页查询
     * @param recordIndex
     * @param limit
     * @return
     */
    List<News> getList(Integer recordIndex, Integer limit);

    int delete(Long id);
}
