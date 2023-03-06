package com.innowireless.web.api;

import com.innowireless.web.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DefaultExceptionResponse handleDefaultException(Exception e, HttpServletResponse res) {
        log.error("DefaultExceptionHandler.handleException: ", e);

        ResponseStatus rs = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        res.setStatus((rs != null) ? rs.value().value() : HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new DefaultExceptionResponse(e);
    }

    public static class DefaultExceptionResponse {
        public final int code;
        public final String message;

        public DefaultExceptionResponse(Exception e) {
            if (e == null) {
                code = ErrorCodes.GENERIC;
                message = "null exception was passed to DefaultExceptionHandler";
            } else if (e instanceof DuplicateKeyException) {
                code = ErrorCodes.DUPLICATED_DATA;

                Throwable rc = ((DuplicateKeyException) e).getRootCause();
                message = (rc == null) ? e.toString() : rc.getMessage();
            } else if (e instanceof DataIntegrityViolationException) {
                code = ErrorCodes.INTEGRITY_VIOLATION_ERROR;
                message = CommonUtil.getExceptionReasons(e);
            } else {
                code = ErrorCodes.GENERIC;
                message = CommonUtil.getExceptionReasons(e);
            }
        }
    }
}
