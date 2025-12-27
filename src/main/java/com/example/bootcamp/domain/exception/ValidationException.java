package com.example.bootcamp.domain.exception;

import com.example.bootcamp.domain.exception.error.CommonErrorCode;
import com.example.bootcamp.domain.exception.error.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder

public class ValidationException extends BusinessException{
    private static final IErrorCode DEFAULT_ERROR_CODE = CommonErrorCode.VALIDATION_ERROR;
    private static final String DEFAULT_CODE = DEFAULT_ERROR_CODE.getCode();
    private static final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();

    public ValidationException(String message) {
        super(DEFAULT_ERROR_CODE, message);
    }

    public ValidationException(IErrorCode iErrorCode) {
        super(iErrorCode);
    }

    public ValidationException(IErrorCode ierrorCode, String message) {
        super(ierrorCode, message);
    }

    public ValidationException(String code, String message) {
        super(code, message, STATUS_CODE);
    }
}
