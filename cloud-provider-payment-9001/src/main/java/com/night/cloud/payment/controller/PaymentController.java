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

    @PostMapping("add")
    public Result addPayment(@RequestBody PaymentModel paymentModel) {
        Integer count = paymentService.insert(paymentModel);
        log.info("serverPort:" + serverPort + ":*******:" + count);
        return Result.success();
    }

    @GetMapping("details/{id}")
    public Result details(@PathVariable("id") Long id) {
        PaymentModel payment = paymentService.getById(id);
        payment.setSerial(payment.getSerial() + "--" + serverPort);
        log.info("serverPort:" + serverPort + ":*******:" + payment);
        return Result.success(payment);
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
