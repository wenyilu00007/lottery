package com.wyl.lotterytask.gecco.core.pipeline;

import com.alibaba.fastjson.JSON;
import com.wyl.lotterytask.gecco.core.annotation.PipelineName;
import com.wyl.lotterytask.gecco.core.spider.SpiderBean;

@PipelineName("consolePipeline")
public class ConsolePipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println(JSON.toJSONString(bean));
	}

}
