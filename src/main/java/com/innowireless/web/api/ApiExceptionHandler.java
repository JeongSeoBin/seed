package com.innowireless.web.api;

import com.innowireless.web.util.CommonUtil;
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
public class ApiExceptionHandler
{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ApiExceptionResponse handleApiException(ApiException e)
    {
        logger.error("ApiExceptionHandler.handleException: ", e);
        return new ApiExceptionResponse(e);
    }

    public static class ApiExceptionResponse
    {
        public int code;
        public String message;

        public ApiExceptionResponse(ApiException e)
        {
            if (e == null)
            {
                code = ErrorCodes.INTERNAL_ERROR;
                message = "null exception was passed to ApiExceptionHandler";
            }
            else
            {
                code = e.code;
                message = CommonUtil.getExceptionReasons(e);
            }
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
}