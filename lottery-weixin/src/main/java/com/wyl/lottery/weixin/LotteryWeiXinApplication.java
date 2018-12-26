package com.wyl.lottery.weixin;

import com.wyl.lottery.weixin.web.config.WeixinConfigPorperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.wyl.*")
@MapperScan("com.wyl.**.dao")
@EnableConfigurationProperties(WeixinConfigPorperties.class)
public class LotteryWeiXinApplication implements WebMvcConfigurer {

	@Value("${server.servlet.context-path}")
	private String weixinContextPath;

	public static void main(String[] args) {
		SpringApplication.run(LotteryWeiXinApplication.class, args);
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(weixinContextPath + "/**").addResourceLocations("classpath:/static/");
	}
}
