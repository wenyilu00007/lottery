package com.wyl.lotterytask.gecco.core.downloader.proxy;

import com.wyl.lotterytask.gecco.core.spider.Spider;
import com.wyl.lotterytask.gecco.core.spider.SpiderThreadLocal;

public class ProxysContext {
	
	public static Proxys get() {
		Spider spider = SpiderThreadLocal.get();
		if(spider == null) {
			return null;
		}
		return spider.getEngine().getProxysLoader();
	}
	
	public static boolean isEnableProxy() {
		Spider spider = SpiderThreadLocal.get();
		if(spider == null) {
			return false;
		}
		return spider.getEngine().isProxy();
	}

}
