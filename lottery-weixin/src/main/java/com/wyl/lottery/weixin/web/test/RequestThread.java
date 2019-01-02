package com.wyl.lottery.weixin.web.test;

import com.wyl.lotterycommon.utils.http.HttpUtil;

public class RequestThread extends Thread {


    @Override
    public void run() {

        while (true){

            HttpUtil.doGet("http://192.168.1.25/member/login");
        }
    }
}
