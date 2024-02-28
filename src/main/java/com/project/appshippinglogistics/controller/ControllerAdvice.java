package com.project.appshippinglogistics.controller;

import com.project.appshippinglogistics.controller.dto.exception.ExceptionControlMessage;
import com.project.appshippinglogistics.controller.dto.exception.ExceptionResponseMessage;
import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.exception.MessagingException;
import com.project.appshippinglogistics.exception.NotFoundException;
import com.project.appshippinglogistics.exception.ResponseMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ExceptionResponseMessage> runtimeExceptionHandler(RuntimeException ex) {
        ExceptionResponseMessage error = ExceptionResponseMessage.builder().code("500").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ExceptionResponseMessage> businessExceptionHandler(BusinessException ex) {
        ExceptionResponseMessage error = ExceptionResponseMessage.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionResponseMessage> notFoundExceptionHandler(NotFoundException ex) {
        ExceptionResponseMessage error = ExceptionResponseMessage.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = MessagingException.class)
    public ResponseEntity<ExceptionResponseMessage> messagingExceptionHandler(MessagingException ex) {
        ExceptionResponseMessage error = ExceptionResponseMessage.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = ResponseMessageException.class)
    public ResponseEntity<ExceptionControlMessage> responseStatusExceptionHandler(ResponseMessageException ex) {
        ExceptionControlMessage error = ExceptionControlMessage.builder().code(ex.getCode()).message(ex.getMessage()).keyValueExceptionsMessages(ex.getKeyValueExceptionsMessages()).build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
}