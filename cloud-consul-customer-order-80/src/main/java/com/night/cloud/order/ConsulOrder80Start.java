package com.night.cloud.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 夜月
 * @date 2020/5/27
 */
@MapperScan("com.night.cloud.order.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulOrder80Start {

    public static void main(String[] args) {
        SpringApplication.run(ConsulOrder80Start.class, args);
    }

}
