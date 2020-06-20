package com.night.cloud.payment.iservice;


import com.night.cloud.common.model.PaymentModel;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 14:25
 */
public interface IPaymentService {


    Integer insert(PaymentModel paymentModel);


    PaymentModel getById(Long id);

}
