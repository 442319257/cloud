package com.night.cloud.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 夜月
 * @date 2020/5/27
 */
@MapperScan("com.night.cloud.order.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class FeignHystrixOrder80Start {

    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixOrder80Start.class, args);
    }

}
