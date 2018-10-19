package com.wyl.lotterytask.gecco.core.pipeline;

import com.wyl.lotterytask.gecco.core.spider.SpiderBean;

public interface PipelineFactory {
	
	public Pipeline<? extends SpiderBean> getPipeline(String name);

}
