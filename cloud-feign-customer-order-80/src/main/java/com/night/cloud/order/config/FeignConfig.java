package com.night.cloud.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 夜月
 * @description 配置Feign客户端日志级别
 * @data 2020/6/11 18:43
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

}
