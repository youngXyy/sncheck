package com.ruijie.sncheck.common.error;

import org.springframework.http.HttpStatus;

/**
 * ApiException
 *
 * @author {yuanwei}
 * @date 2019/5/6 17:16
 */
public class ApiException extends RuntimeException {
    private int status;
    private Object detail;
    private ResultCode resultCode = ResultCode.ERROR;
    public ApiException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public ApiException(Exception exception) {
        this(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    public ApiException(int status, Object detail) {
        super(detail.toString());
        this.status = status;
        this.detail = detail;

    }


    public static ApiException badRequest(String formatText, Object ...parms) {
        return new ApiException(ResultCode.ERROR.getCode(),String.format(formatText,parms));
    }
    public static ApiException serviceException() {
        ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR);
        apiException.setResultCode(ResultCode.SERVICE_EXCEPTION);
        return apiException;
    }
    public ApiException(HttpStatus status) {
        this(status, status.getReasonPhrase());
    }

    public ApiException(HttpStatus status, String detailFormat, Object... objects) {
        this(status.value(), String.format(detailFormat, objects));
    }

    public ApiExceptionResponse getResponse() {
        return new ApiExceptionResponse(this.getStatus(), this.getDetail().toString());
    }

    public int getStatus() {
        return status;
    }

    public Object getDetail() {
        return detail;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
