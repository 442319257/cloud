package com.night.cloud.order.iservice;

import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
import com.night.cloud.order.service.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 夜月
 * @description
 * @data 2020/6/11 17:53
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = PaymentFallbackServiceImpl.class)
public interface IPaymentService {

    @GetMapping("payment/hystrix/ok/{id}")
    Result<String> hystrixOk(@PathVariable("id") Long id);

    @GetMapping("payment/hystrix/timeout/{id}")
    Result<String> hystrixTimeOut(@PathVariable("id") Long id);

}
