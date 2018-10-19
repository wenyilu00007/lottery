package com.wyl.lotterytask.gecco.core.scheduler;

import com.wyl.lotterytask.gecco.core.request.HttpRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 不需要循环抓取的start队列
 * 
 * @author huchengyi
 *
 */
public class NoLoopStartScheduler implements Scheduler {
	
	private ConcurrentLinkedQueue<HttpRequest> queue;
	
	public NoLoopStartScheduler() {
		queue = new ConcurrentLinkedQueue<HttpRequest>();
	}

	@Override
	public HttpRequest out() {
		HttpRequest request = queue.poll();
		return request;
	}

	@Override
	public void into(HttpRequest request) {
		queue.offer(request);
	}

}
