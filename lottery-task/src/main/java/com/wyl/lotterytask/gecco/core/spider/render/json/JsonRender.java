package com.wyl.lotterytask.gecco.core.spider.render.json;

import com.wyl.lotterytask.gecco.core.request.HttpRequest;
import com.wyl.lotterytask.gecco.core.response.HttpResponse;
import com.wyl.lotterytask.gecco.core.spider.SpiderBean;
import com.wyl.lotterytask.gecco.core.spider.render.AbstractRender;
import net.sf.cglib.beans.BeanMap;

/**
 * 将下载下来的json映射到bean中
 * 
 * @author huchengyi
 *
 */
public class JsonRender extends AbstractRender {
	
	private JsonFieldRender jsonFieldRender;
	
	public JsonRender() {
		super();
		this.jsonFieldRender = new JsonFieldRender();
	}

	@Override
	public void fieldRender(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		jsonFieldRender.render(request, response, beanMap, bean);
	}

}