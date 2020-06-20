package com.night.cloud.order;

import com.night.cloud.rule.PaymentRibbonRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author 夜月
 * @date 2020/5/27
 */
@MapperScan("com.night.cloud.order.mapper")
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = PaymentRibbonRule.class)
public class Order80Start {

    public static void main(String[] args) {
        SpringApplication.run(Order80Start.class, args);
    }

}
