package com.wyl.lotterycommon.dao;

import com.wyl.lotterycommon.domain.News;
import com.wyl.lotterydatasource.mybatis.basedao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 温奕禄
 * @date 2017/9/21 16:47
 */
public interface NewsDao extends BaseDao<News> {

    void batchInsert(List<News> list);
}
