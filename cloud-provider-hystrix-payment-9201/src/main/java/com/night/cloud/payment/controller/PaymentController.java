package com.night.cloud.payment.controller;

import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
import com.night.cloud.payment.iservice.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 14:30
 */
@Slf4j
@RequestMapping("/payment/")
@RestController
public class PaymentController {

    @Resource(name = "paymentService")
    private IPaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;


    @Value("${server.port}")
    private String serverPort;

    @GetMapping("hystrix/ok/{id}")
    public Result hystrixOk(@PathVariable("id") Long id) {
        String content = paymentService.hystrixOk(id);
        log.info("serverPort:" + serverPort + ":*******:" + content);
        return Result.success(content);
    }

    @GetMapping("hystrix/timeout/{id}")
    public Result hystrixTimeOut(@PathVariable("id") Long id) {
        String content = paymentService.hystrixTimeOut(id);
        log.info("serverPort:" + serverPort + ":*******:" + content);
        return Result.success(content);
    }

    @GetMapping("discovery")
    public Result discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service: " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("host: " + instance.getHost() + "\tport:" + instance.getPort() + "\turi:" + instance.getUri());
        }
        return Result.success(discoveryClient);
    }

    @GetMapping("lb")
    public Result lb() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success(serverPort);
    }

}
