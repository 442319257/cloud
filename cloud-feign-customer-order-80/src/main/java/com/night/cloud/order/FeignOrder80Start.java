package com.night.cloud.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 夜月
 * @date 2020/5/27
 */
@MapperScan("com.night.cloud.order.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignOrder80Start {

    public static void main(String[] args) {
        SpringApplication.run(FeignOrder80Start.class, args);
    }

}
