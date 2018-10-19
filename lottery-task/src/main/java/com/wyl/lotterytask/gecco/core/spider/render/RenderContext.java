package com.wyl.lotterytask.gecco.core.spider.render;

import com.wyl.lotterytask.gecco.core.spider.SpiderThreadLocal;

public class RenderContext {
	
	public static Render getRender(RenderType type){
		return SpiderThreadLocal.get().getEngine().getSpiderBeanFactory().getRenderFactory().getRender(type);
	}

}
