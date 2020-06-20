package com.night.cloud.order.service;

import com.night.cloud.common.result.Result;
import com.night.cloud.common.result.ResultCode;
import com.night.cloud.order.iservice.IPaymentService;
import org.springframework.stereotype.Component;

/**
 * @author 夜月
 * @description
 * @data 2020/6/20 18:01
 */
@Component
public class PaymentFallbackServiceImpl implements IPaymentService {

    @Override
    public Result<String> hystrixOk(Long id) {
        return Result.error(ResultCode.bizRuntime);
    }

    @Override
    public Result<String> hystrixTimeOut(Long id) {
        return Result.error(ResultCode.bizRuntime);
    }
}
