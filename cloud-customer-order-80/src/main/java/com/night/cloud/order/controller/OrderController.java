package com.night.cloud.order.controller;

import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 16:12
 */
@RequestMapping("/consumer/")
@RestController
public class OrderController {

    // private static final String PAYMENT_URL = "http://localhost:9001/";
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("payment/add")
    public Result insert(PaymentModel paymentModel) {
        return restTemplate.postForObject(PAYMENT_URL + "payment/add", paymentModel, Result.class);
    }

    @GetMapping("payment/details/{id}")
    public Result details(@PathVariable("id") String id) {
        return restTemplate.getForObject(PAYMENT_URL + "payment/details/" + id, Result.class);
    }

}
