package com.wyl.lotterytask.gecco.configruation;

import com.wyl.lotterytask.gecco.core.GeccoEngine;
import com.wyl.lotterytask.gecco.springboot.SpringGeccoEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeccoEngineConfiguration {


    @Bean("githubEngine")
    public SpringGeccoEngine initGecco() {
        return new SpringGeccoEngine() {
            @Override
            public void init() {
                GeccoEngine.create()
                        .pipelineFactory(springPipelineFactory)
                        .classpath("com.wyl.lotterytask.gecco")
                        .start("http://news.baidu.com")
                        .interval(3600000)
                        .loop(true)
                        .start();
            }
        };
    }

}
