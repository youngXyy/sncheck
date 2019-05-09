package com.ruijie.sncheck.common.error;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ApiExceptionResponse
 *
 * @author {yuanwei}
 * @date 2019/5/6 17:18
 */
public class ApiExceptionResponse {
    @JsonProperty("status")
    private int status;
    @JsonProperty("detail")
    private String detail;

    public ApiExceptionResponse(int status, String detail) {
        this.status = status;
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
