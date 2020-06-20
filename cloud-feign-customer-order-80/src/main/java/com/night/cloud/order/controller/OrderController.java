package com.night.cloud.order.controller;

import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
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
public class OrderController {

    @Resource
    private IPaymentService paymentService;

    @GetMapping("payment/details/{id}")
    public Result<PaymentModel> details(@PathVariable("id") Long id) {
        return paymentService.details(id);
    }

    @GetMapping("payment/lb")
    public Result<String> lb() {
        return paymentService.lb();
    }


}
