package com.night.cloud.payment.iservice;


import com.night.cloud.common.model.PaymentModel;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 14:25
 */
public interface IPaymentService {


    String hystrixOk(Long id);


    String hystrixTimeOut(Long id);

    /**
     *
     * 服务熔断测试
     *
     * @param
     * @return
     * @author by 夜月
     * @since 2020/6/21 0021
     */
    String hystrixCircuitBreaker(Long id);


}
