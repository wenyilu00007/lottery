package com.wyl.lotterytask.gecco.springboot;

import com.alibaba.fastjson.JSON;
import com.wyl.lotterytask.gecco.core.pipeline.Pipeline;
import com.wyl.lotterytask.gecco.core.spider.SpiderBean;

public class ConsolePipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println(JSON.toJSONString(bean));
	}

}
