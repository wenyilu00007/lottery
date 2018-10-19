package com.wyl.lotterycommon.service.impl;

import com.wyl.lotterycommon.dao.NewsDao;
import com.wyl.lotterycommon.domain.News;
import com.wyl.lotterycommon.privoder.InsertDbPrivoder;
import com.wyl.lotterycommon.service.NewsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;
    @Autowired
    private InsertDbPrivoder insertDbPrivoder;
    @Override
    public void saveNews(List<News> saveNews) {
        for (News saveNew : saveNews) {
            insertDbPrivoder.saveBuild(saveNew);
        }
        newsDao.batchInsert(saveNews);
    }

    @Override
    public List<News> getList(Integer recordIndex, Integer limit) {
       return newsDao.selectByRowBounds(new News(),new RowBounds(recordIndex,limit));
    }

    @Override
    public int delete(Long id) {

        Function<Long,News> function = (i)->{
            News news = new News();
            news.setId(i);
            return news;
        };
        return newsDao.delete(function.apply(id));
    }
}
