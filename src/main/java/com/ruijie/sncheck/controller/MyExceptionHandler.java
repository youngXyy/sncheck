package com.ruijie.sncheck.controller;

import com.ruijie.sncheck.common.error.ApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * MyExceptionHandler
 *
 * @author {yuanwei}
 * @date 2019/5/13 13:51
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public Map<String,Object> handleException(ApiException e){
        Map<String,Object> map = new HashMap<>();
        map.put("status",e.getStatus());
        map.put("message",e.getMessage());
        return map;
    }
}
