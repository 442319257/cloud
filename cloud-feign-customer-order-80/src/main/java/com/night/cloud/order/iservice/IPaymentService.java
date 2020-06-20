package com.night.cloud.order.iservice;

import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.common.result.Result;
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
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface IPaymentService {

    @GetMapping("payment/details/{id}")
    Result<PaymentModel> details(@PathVariable("id") Long id);

    @GetMapping("payment/lb")
    Result<String> lb();

}
