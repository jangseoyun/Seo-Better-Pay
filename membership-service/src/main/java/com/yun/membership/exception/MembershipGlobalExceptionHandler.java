package com.yun.membership.exception;

import com.yun.membership.adapter.in.web.model.MembershipResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MembershipGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(MembershipModuleException e) {
        return ResponseEntity
                .status(e.getMembershipErrorCode().getStatus())
                .body(MembershipResult.error(e.getMessage()));
    }

    @ExceptionHandler(MembershipModuleException.class)
    public ResponseEntity<?> AwesomeAppExceptionHandler(MembershipModuleException appEx) {
        ErrorResult errorResult = new ErrorResult(appEx.getMembershipErrorCode(), appEx.getMessage());
        return ResponseEntity
                .status(appEx.getMembershipErrorCode().getStatus())
                .body(MembershipResult.error(errorResult));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         final WebRequest request)
    {
        logger.debug("HttpRequestMethodNotSupported : {} ", ex);
        ErrorResult errorResult = new ErrorResult(ex.getStatusCode(), ex.getMessage());
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(MembershipResult.error(errorResult));
    }
}
