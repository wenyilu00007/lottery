package com.wyl.lotteryweb.controller;

import com.wyl.lottery.domain.dto.WYLResult;
import com.wyl.lotterycommon.domain.News;
import com.wyl.lotterycommon.service.NewsService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {

    public static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @GetMapping("news/list")
    public WYLResult<List<News>> getList(@RequestParam("recordIndex") Integer recordIndex,
                                         @RequestParam("limit") Integer limit) {
        WYLResult<List<News>> result = new WYLResult<>(HttpStatus.SC_OK, null, true);
        try {
            List<News> data = newsService.getList(recordIndex, limit);
            result.setData(data);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            result.setMessage(e.getMessage());
            result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
