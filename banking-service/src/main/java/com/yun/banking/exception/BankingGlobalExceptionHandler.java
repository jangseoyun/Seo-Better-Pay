package com.yun.banking.exception;

import com.yun.banking.adapter.in.web.model.BankingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BankingGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(BankingModuleException e) {
        return ResponseEntity
                .status(e.getBankingServiceErrorCode().getStatus())
                .body(BankingResult.error(e.getMessage()));
    }

    @ExceptionHandler(BankingModuleException.class)
    public ResponseEntity<?> AwesomeAppExceptionHandler(BankingModuleException appEx) {
        ErrorResult errorResult = new ErrorResult(appEx.getBankingServiceErrorCode(), appEx.getMessage());
        return ResponseEntity
                .status(appEx.getBankingServiceErrorCode().getStatus())
                .body(BankingResult.error(errorResult));
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
                .body(BankingResult.error(errorResult));
    }
}
