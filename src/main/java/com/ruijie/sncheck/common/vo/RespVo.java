package com.ruijie.sncheck.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruijie.sncheck.common.error.ApiException;
import com.ruijie.sncheck.common.error.ApiExceptionResponse;
import com.ruijie.sncheck.common.error.ResultCode;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Optional;

/**
 * RespVo
 *
 * @author {yuanwei}
 * @date 2019/5/7 10:52
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@EqualsAndHashCode
public class RespVo<T> implements Serializable {
    private T body;
    private int status = ResultCode.OK.getCode();
    private String message = ResultCode.OK.getDesc();
    private ApiExceptionResponse error = null;
    public RespVo(){}
    public RespVo(T body) {
        this.body = body;
    }
    public RespVo(HttpStatus httpStatus) {

        if(httpStatus.isError()){
            this.setStatus(ResultCode.ERROR.getCode());
            this.setMessage(ResultCode.ERROR.getDesc());
            error = new ApiExceptionResponse(httpStatus.value(),httpStatus.getReasonPhrase());
        } else {
            this.setStatus(ResultCode.OK.getCode());
            this.setMessage(ResultCode.OK.getDesc());
        }

    }

    public RespVo(ResultCode resultCode) {
        this(null,resultCode);
    }

    public RespVo(T body, ResultCode resultCode) {
        this.body = body;
        this.status=resultCode.getCode();
        this.message=resultCode.getDesc();
    }

    public RespVo(T body,int status, String message) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public RespVo(T body, int status, String message, int errorCode, String errorInfo){
        this(body,status,message);
        this.error = new ApiExceptionResponse(errorCode,errorInfo);
    }

    public static RespVoBuild status(HttpStatus httpStatus) {

        return new DefaultRespVoBuild().status(httpStatus.value()).message(httpStatus.getReasonPhrase());
    }
    public static RespVoBuild status(int status) {
        return new DefaultRespVoBuild().status(status);
    }
    public static RespVoBuild status(ResultCode resultCode) {
        return new DefaultRespVoBuild().status(resultCode);
    }
    public static <T> RespVo<T> ok(T body) {
        return new RespVo<>(body);
    }
    public static RespVoBuild error() {
        return new DefaultRespVoBuild().status(ResultCode.ERROR);
    }
    public static <T> RespVo<T> hystrixError() {
        return new RespVo<>(ResultCode.SERVICE_EXCEPTION);
    }
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(ApiExceptionResponse error) {
        this.error = error;
    }
    public boolean isOk(boolean throwError) {
        if(!isOk()) {
            throw ApiException.serviceException();
        }
        return true;
    }
    @JsonIgnore
    public boolean isOk() {
        return ResultCode.OK.getCode()==this.status;
    }

    public Optional<T> ofBody() {
        return Optional.ofNullable(body);
    }

    public ApiExceptionResponse getError() {
        return error;
    }
}
