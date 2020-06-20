package com.night.cloud.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


/**
 * @author by 夜月
 * @since 2020/4/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回结果
     */
    private T data;

    /**
     * 返回描述
     */
    private String msg;


    private Result(ResultCode type) {
        this.code = type.getCode();
        this.msg = type.getMsg();
    }

    private Result(ResultCode type, T data) {
        this.code = type.getCode();
        this.msg = type.getMsg();
        this.data = data;
    }

    /**
     * 成功返回,无返回对象
     *
     * @return Result<T>
     * @author by 夜月
     * @since 2020/4/8
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.success);
    }

    /**
     * 成功返回,有返回对象
     *
     * @param data 返回对象
     * @return Result<T>
     * @author by 夜月
     * @since 2020/4/8
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.success, data);
    }

    /**
     * 失败返回,无具体错误码
     *
     * @return Result<T>
     * @author by 夜月
     * @since 2020/4/8
     */
    public static <T> Result<T> error() {
        return new Result<>(ResultCode.error);
    }

    /**
     * 失败返回,包含具体错误码
     *
     * @param code 错误码
     * @return Result<T>
     * @author by 夜月
     * @since 2020/4/8
     */
    public static <T> Result<T> error(ResultCode code) {
        return new Result<>(code);
    }

}
