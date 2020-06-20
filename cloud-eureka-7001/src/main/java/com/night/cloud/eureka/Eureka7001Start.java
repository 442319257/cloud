package com.night.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 夜月
 * @date 2020/5/27
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001Start {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Start.class, args);
    }

}
