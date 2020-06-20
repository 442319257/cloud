package com.night.cloud.payment.service;

import com.night.cloud.common.model.PaymentModel;
import com.night.cloud.payment.iservice.IPaymentService;
import com.night.cloud.payment.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 14:25
 */
@Service(value = "paymentService")
public class PaymentServiceImpl implements IPaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public Integer insert(PaymentModel paymentModel) {
        return paymentMapper.insert(paymentModel);
    }

    @Override
    public PaymentModel getById(Long id) {
        return paymentMapper.selectById(id);
    }
}
