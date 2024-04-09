package com.yun.membership.exception;

import com.yun.membership.adapter.in.web.model.MembershipResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        List<ErrorResult> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> new ErrorResult(ex.getStatusCode(), e.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity
                .status(status)
                .body(MembershipResult.error(errors));

    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         final WebRequest request) {

        logger.debug("HttpRequestMethodNotSupported : {} ", ex);
        ErrorResult errorResult = new ErrorResult(ex.getStatusCode(), ex.getMessage());

        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(MembershipResult.error(errorResult));
    }

}
