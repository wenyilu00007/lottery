package com.wyl.lottery.weixin.web.test;

import com.google.common.collect.Lists;

import java.util.List;

public class RequestTest {
    public static void main(String[] args) {
        List<RequestThread> threads = Lists.newArrayList();
        for (int i = 0; i <1 ; i++) {
            RequestThread requestThread = new RequestThread();
            threads.add(requestThread);
            requestThread.start();
        }

        for (RequestThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
