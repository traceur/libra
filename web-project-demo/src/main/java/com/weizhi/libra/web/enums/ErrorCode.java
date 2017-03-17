
package com.weizhi.libra.web.enums;

/**
 * @author weizhi
 * @version Id: TradeErrorCode.java, v 0.1 2016/8/8 16:33  Exp $$
 * @Description TODO
 */
public enum ErrorCode {

    SUCCESS("000000", "成功"),

    TRD_PAY_TIMEOUT("400003", "支付处理超时,暂不发货"),


    FAIL_UNKNOWN_ERROR("999999", "未知异常(不能当失败)");

    /**
     * 错误码属性
     */
    private String code;

    /**
     * 错误信息提示内容
     */
    private String msg;


    private ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
