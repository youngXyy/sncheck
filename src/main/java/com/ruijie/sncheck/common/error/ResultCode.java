package com.ruijie.sncheck.common.error;

/**
 * ResultCode
 *
 * @author {yuanwei}
 * @date 2019/5/6 17:17
 */
public enum ResultCode {
    /**
     * 返回数据字典
     */
    OK(0, "OK"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    SERVICE_EXCEPTION(3, "服务异常"),
    WARN(4, "服务正常,数据异常");

    private final Integer code;
    private final String desc;
    private static final long serialVersionUID = 1L;

    ResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
