package com.wyl.lotterytask.gecco.springboot;

import com.wyl.lotterytask.gecco.core.pipeline.baidu.BaiduSortPipeline;
import com.wyl.lotterytask.gecco.core.pipeline.jd.AllSortPipeline;
import com.wyl.lotterytask.gecco.core.pipeline.jd.ProductListPipeline;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {

	@Bean
	public SpringPipelineFactory springPipelineFactory() {
		return new SpringPipelineFactory();
	}

	@Bean(name="consolePipeline")
	public ConsolePipeline consolePipeline() {
		return new ConsolePipeline();
	}
	@Bean(name="allSortPipeline")
	public AllSortPipeline allSortPipeline() {
		return new AllSortPipeline();
	}
	@Bean(name="productListPipeline")
	public ProductListPipeline productListPipeline() {
		return new ProductListPipeline();
	}
	@Bean(name="baiduSortPipeline")
	public BaiduSortPipeline baiduSortPipeline() {
		return new BaiduSortPipeline();
	}
}
