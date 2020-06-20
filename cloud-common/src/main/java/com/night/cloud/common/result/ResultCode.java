package com.night.cloud.common.result;

/**
 * 响应状态枚举
 *
 * @author by 夜月
 * @since 2020/4/8
 */
public enum ResultCode {
    //
    error(-1, "未知错误"),
    success(0, "成功"),
    loginFail(100001, "登录失败"),
    illegalArgument(101001, "参数不合法"),
    bizRuntime(102001, "业务处理异常");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
