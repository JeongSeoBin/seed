package com.innowireless.web.api;

import com.innowireless.web.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ApiExceptionResponse handleApiException(ApiException e) {
        log.error("ApiExceptionHandler.handleException: ", e);
        return new ApiExceptionResponse(e);
    }

    public static class ApiExceptionResponse {
        public int code;
        public String message;

        public ApiExceptionResponse(ApiException e) {
            if (e == null) {
                code = ErrorCodes.INTERNAL_ERROR;
                message = "null exception was passed to ApiExceptionHandler";
            } else {
                code = e.code;
                message = CommonUtil.getExceptionReasons(e);
            }
        }
    }
}
