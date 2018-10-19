package com.wyl.lottery.config;

import com.wyl.lottery.service.id.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdConfigruation {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(){
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 1);
        return snowflakeIdWorker;
    }
}