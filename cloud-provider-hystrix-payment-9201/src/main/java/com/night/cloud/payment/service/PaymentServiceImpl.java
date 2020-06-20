package com.night.cloud.payment.service;

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

}
