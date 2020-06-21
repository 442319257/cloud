package com.night.cloud.payment.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.night.cloud.payment.iservice.IPaymentService;
import com.night.cloud.payment.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 14:25
 */
@Service(value = "paymentService")
public class PaymentServiceImpl implements IPaymentService {

    @Override
    public String hystrixOk(Long id) {
        return "线程池: " + Thread.currentThread().getName() + "  hystrixOk, id: " + id + "  O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "hystrixTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String hystrixTimeOut(Long id) {
        int time = 5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + "  hystrixTimeOut, id: " + id + "  哦哦, 耗时(秒): " + time;
    }

    public String hystrixTimeOutHandler(Long id) {
        return "线程池: " + Thread.currentThread().getName() + "  hystrixTimeOutHandler, id: " + id;
    }

    @HystrixCommand(fallbackMethod = "hystrixCircuitBreakerHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 开启断路器
            // 在时间窗口期10秒内,请求次数达到10次,请求失败率达到60%,会熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), // 失败率达到多少后熔断
    })
    @Override
    public String hystrixCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String serialNum = IdUtil.simpleUUID();

        return "线程池: " + Thread.currentThread().getName() + "  hystrixCircuitBreaker, serialNum: " + serialNum;
    }

    public String hystrixCircuitBreakerHandler(Long id) {
        return "id不能为负数, id: " + id;
    }
}
