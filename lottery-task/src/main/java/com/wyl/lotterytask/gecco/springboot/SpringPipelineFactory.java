package com.wyl.lotterytask.gecco.springboot;

import com.wyl.lotterytask.gecco.core.pipeline.Pipeline;
import com.wyl.lotterytask.gecco.core.pipeline.PipelineFactory;
import com.wyl.lotterytask.gecco.core.spider.SpiderBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringPipelineFactory implements PipelineFactory, ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public Pipeline<? extends SpiderBean> getPipeline(String name) {
		try {
			Object bean = applicationContext.getBean(name);
			if(bean instanceof Pipeline) {
				return (Pipeline<? extends SpiderBean>)bean;
			}
		} catch(NoSuchBeanDefinitionException ex) {
			System.out.println("no such pipeline : " + name);
		}
		return null;
	}

}
