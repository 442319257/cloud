package com.night.cloud.payment.controller;

import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
import com.night.cloud.payment.iservice.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("add")
    public Result addPayment(@RequestBody PaymentModel paymentModel) {
        Integer count = paymentService.insert(paymentModel);
        log.info("serverPort:" + serverPort + ":*******:" + count);
        return Result.success();
    }

    @GetMapping("details/{id}")
    public Result addPayment(@PathVariable("id") Long id) {
        PaymentModel payment = paymentService.getById(id);
        payment.setSerial(payment.getSerial() + "--" + serverPort);
        log.info("serverPort:" + serverPort + ":*******:" + payment);
        return Result.success(payment);
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
