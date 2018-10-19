package com.wyl.lotterytask.gecco.springboot;

import com.wyl.lotterytask.gecco.core.pipeline.PipelineFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;

public abstract class SpringGeccoEngine implements ApplicationListener<ContextRefreshedEvent> {

	@Resource
	protected PipelineFactory springPipelineFactory;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null) {
			init();
		}
	}

	/**
	 * spring下启动gecco的初始化方法，spring下必须要用非阻塞方式start()运行GeccoEngine
	 */
	public abstract void init();
}
