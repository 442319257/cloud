package com.night.cloud.common.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 夜月
 * @description
 * @data 2020/5/27 14:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("night_payment")
public class PaymentModel implements Serializable {

    @TableId
    private Long id;


    private String serial;

}
