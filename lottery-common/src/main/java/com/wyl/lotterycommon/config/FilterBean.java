package com.wyl.lotterycommon.config;

import com.wyl.lotterycommon.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterBean {

    @Bean
    public CorsFilter corsFilter() {
        CorsFilter corsFilter = new CorsFilter();
        return corsFilter;
    }
}
