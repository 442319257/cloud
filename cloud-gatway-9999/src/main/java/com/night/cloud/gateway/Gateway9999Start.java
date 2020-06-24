package com.night.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 夜月
 * @date 2020/5/27
 */
@SpringBootApplication
@EnableEurekaClient
public class Gateway9999Start {

    public static void main(String[] args) {
        SpringApplication.run(Gateway9999Start.class, args);
    }

}
