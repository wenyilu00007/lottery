package com.wyl.lotterytask.gecco.core.downloader;

import com.wyl.lotterytask.gecco.core.request.HttpRequest;
import com.wyl.lotterytask.gecco.core.response.HttpResponse;
import com.wyl.lotterytask.gecco.core.spider.SpiderBeanContext;
import com.wyl.lotterytask.gecco.core.spider.SpiderThreadLocal;

/**
 * 获得当前线程，正在抓取的SpiderBean的下载器
 * 
 * @author huchengyi
 *
 */
public class DownloaderContext {
	
	public static HttpResponse download(HttpRequest request) throws DownloadException {
		SpiderBeanContext context = SpiderThreadLocal.get().getSpiderBeanContext();
		return context.getDownloader().download(request, context.getTimeout());
	}
	
	public static HttpResponse defaultDownload(HttpRequest request) throws DownloadException {
		SpiderBeanContext context = SpiderThreadLocal.get().getSpiderBeanContext();
		Downloader downloader = SpiderThreadLocal.get().getEngine().getSpiderBeanFactory().getDownloaderFactory().defaultDownloader();
		return downloader.download(request, context.getTimeout());
	}
	

}
