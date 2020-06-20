package com.night.cloud.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 夜月
 * @description ribbon设置随机算法
 * @data 2020/6/10 14:54
 */
@Configuration
public class PaymentRibbonRule {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

}
