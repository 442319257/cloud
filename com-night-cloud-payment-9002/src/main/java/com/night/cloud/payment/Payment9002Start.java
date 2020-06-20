package com.night.cloud.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 夜月
 * @date 2020/5/27
 */
@MapperScan("com.night.cloud.payment.mapper")
@SpringBootApplication
@EnableEurekaClient
public class Payment9002Start {

    public static void main(String[] args) {
        SpringApplication.run(Payment9002Start.class, args);
    }

}
