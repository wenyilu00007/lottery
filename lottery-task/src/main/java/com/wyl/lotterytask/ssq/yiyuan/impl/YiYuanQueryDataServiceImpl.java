package com.wyl.lotterytask.ssq.yiyuan.impl;

import com.alibaba.fastjson.JSON;
import com.wyl.lotterytask.ssq.yiyuan.ResponseData;
import com.wyl.lotterytask.ssq.yiyuan.YiYuanQueryDataService;
import com.wyl.lotterytask.ssq.yiyuan.base.ShowApiRequest;
import com.wyl.lotterytask.ssq.yiyuan.base.YiYuanUtil;
import com.wyl.lotterytask.utils.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class YiYuanQueryDataServiceImpl implements YiYuanQueryDataService {

    @Override
    public ResponseData getSsq() {
        ResponseData responseData = null;
        String res =new ShowApiRequest("http://route.showapi.com/44-1", YiYuanUtil.appId, YiYuanUtil.appSecret)
                .addTextPara("code", "ssq")
                .post();
        if(!StringUtils.isEmpty(res)){
            responseData = JSON.parseObject(res, ResponseData.class);
        }
        return responseData;
    }

    public static void main(String[] args) {
        ResponseData responseData = null;
        String res =new ShowApiRequest("http://route.showapi.com/44-1", YiYuanUtil.appId, YiYuanUtil.appSecret)
                .addTextPara("code", "ssq")
                .post();
        if(!StringUtils.isEmpty(res)){
            responseData = JSON.parseObject(res, ResponseData.class);
        }
        System.out.println(res);
    }
}
