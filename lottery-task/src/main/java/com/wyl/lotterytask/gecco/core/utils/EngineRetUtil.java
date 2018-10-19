package com.wyl.lotterytask.gecco.core.utils;

import com.wyl.lotterytask.gecco.core.spider.SpiderThreadLocal;

public class EngineRetUtil{

    public static Object getRet(){
        return SpiderThreadLocal.get().getEngine().getRet();
    }

    public static void setRet(Object o){
        SpiderThreadLocal.get().getEngine().setRet(o);
    }

}
