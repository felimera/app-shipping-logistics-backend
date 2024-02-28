package com.project.appshippinglogistics.controller.dto.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExceptionControlMessage {
    private String code;
    private String message;
    private List<KeyValueExceptionsMessage> keyValueExceptionsMessages;
}
