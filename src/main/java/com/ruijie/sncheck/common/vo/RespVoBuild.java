package com.ruijie.sncheck.common.vo;

import com.ruijie.sncheck.common.error.ApiExceptionResponse;
import com.ruijie.sncheck.common.error.ErrorMessages;
import com.ruijie.sncheck.common.error.ResultCode;

/**
 * RespVoBuild
 *
 * @author {yuanwei}
 * @date 2019/5/7 10:53
 */
public interface RespVoBuild {
    RespVoBuild status(ResultCode resultCode);
    RespVoBuild status(int status);
    RespVoBuild message(String string);
    RespVoBuild error(ErrorMessages errorMessages);
    RespVoBuild error(int status, String detail);
    RespVoBuild error(ApiExceptionResponse apiExceptionResponse);
    <T> RespVo<T> body(T body);
    <T> RespVo<T> build();
}
