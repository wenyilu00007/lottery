package com.wyl.lottery.weixin.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wyl.weixin",ignoreInvalidFields = false)
@Data
public class WeixinConfigPorperties {
    private String appid;
    private String secret;
    private String token;
}
