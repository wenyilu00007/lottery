package com.wyl.lotterytask.ssq.quarz;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.google.common.collect.Maps;
import com.wyl.lotterytask.ssq.yiyuan.ResponseBody;
import com.wyl.lotterytask.ssq.yiyuan.ResponseData;
import com.wyl.lotterytask.ssq.yiyuan.ResultData;
import com.wyl.lotterytask.ssq.yiyuan.YiYuanQueryDataService;
import com.wyl.lotterytask.utils.DateUtil;
import com.wyl.lotterytask.utils.aliyunSendSmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static java.util.Calendar.DAY_OF_WEEK;

@Component
public class QueryLotteryDataQuarz {

    public static Logger logger = LoggerFactory.getLogger(QueryLotteryDataQuarz.class);

    @Autowired
    private YiYuanQueryDataService yiYuanQueryDataService;

//    @Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(cron = "0 0 22 1/1 * ?")
    public void cronSendSsq() {
        logger.info("定时任务执行查询号码.....");
        try {
//            if (lotteryDay()) {
            ResponseData ssq = yiYuanQueryDataService.getSsq();
            aliyunSendSmsUtil.sendSms("19945657236,18048514526,15027198261", getConten(ssq));
//            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private String getConten(ResponseData ssq) {
        String content = "";
        if (ssq != null) {
            ResponseBody showapi_res_body = ssq.getShowapi_res_body();
            if (showapi_res_body != null) {
                List<ResultData> result = showapi_res_body.getResult();
                if (!CollectionUtils.isEmpty(result)) {
                    String openCode = result.get(0).getOpenCode();
                    String time = result.get(0).getTime();

                    Map<String, Object> param = Maps.newHashMap();
                    param.put("number", openCode);
                    param.put("date", addWeekDay(time));
                    content = JSON.toJSONString(param);
                }
            }
        }
        return content;
    }


    private boolean lotteryDay() {
        int[] lotteryDay = new int[]{1, 3, 5};
        Calendar instance = Calendar.getInstance();
        int dayofweek = instance.get(DAY_OF_WEEK);
        return Arrays.asList(lotteryDay).contains(dayofweek);
    }

    private String addWeekDay(String date){
        Date tmpDate = DateUtil.parseDate(date);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(tmpDate);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return String.format("%s(%s)",DateUtil.format(tmpDate,"yyyy-MM-dd"),weekDays[w]);

    }


}
