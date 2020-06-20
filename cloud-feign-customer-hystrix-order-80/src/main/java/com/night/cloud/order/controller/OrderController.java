package com.night.cloud.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
import com.night.cloud.common.result.ResultCode;
import com.night.cloud.order.iservice.IPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 16:12
 */
@RequestMapping("/consumer/")
@RestController
@DefaultProperties(defaultFallback = "globalHystrixHandler")
public class OrderController {

    @Resource
    private IPaymentService paymentService;

    @GetMapping("payment/hystrix/ok/{id}")
    public Result<String> hystrixOk(@PathVariable("id") Long id) {
        return paymentService.hystrixOk(id);
    }

    /*@HystrixCommand(fallbackMethod = "hystrixTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @HystrixCommand
    @GetMapping("payment/hystrix/timeout/{id}")
    public Result<String> hystrixTimeOut(@PathVariable("id") Long id) {
        return paymentService.hystrixTimeOut(id);
    }

    public Result<String> hystrixTimeOutHandler(@PathVariable("id") Long id) {
        return Result.error();
    }

    public Result<String> globalHystrixHandler() {
        return Result.error(ResultCode.bizRuntime);
    }
}
