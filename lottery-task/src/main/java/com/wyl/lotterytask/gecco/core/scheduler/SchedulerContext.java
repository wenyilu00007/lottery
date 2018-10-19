package com.wyl.lotterytask.gecco.core.scheduler;

import com.wyl.lotterytask.gecco.core.request.HttpRequest;
import com.wyl.lotterytask.gecco.core.spider.SpiderThreadLocal;

/**
 * 被DeriveSchedulerContext替代，特指派生队列
 * 
 * @author huchengyi
 *
 */
@Deprecated
public class SchedulerContext {
	
	public static void into(HttpRequest request) {
		SpiderThreadLocal.get().getSpiderScheduler().into(request);
	}

}
