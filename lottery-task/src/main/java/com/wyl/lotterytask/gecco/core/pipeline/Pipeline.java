package com.wyl.lotterytask.gecco.core.pipeline;

import com.wyl.lotterytask.gecco.core.spider.SpiderBean;

public interface Pipeline<T extends SpiderBean> {

	public void process(T bean);

}
