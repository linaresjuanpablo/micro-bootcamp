package com.example.bootcamp.domain.exception;

import com.example.bootcamp.domain.exception.error.CommonErrorCode;
import com.example.bootcamp.domain.exception.error.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder

public class DuplicateResourceException extends BusinessException{

    private static final IErrorCode DEFAULT_ERROR_CODE = CommonErrorCode.RESOURCE_ALREADY_EXISTS;
    private static final String DEFAULT_CODE = DEFAULT_ERROR_CODE.getCode();
    private static final int STATUS_CODE = HttpStatus.CONFLICT.value();

    public DuplicateResourceException(String message) {
        super(DEFAULT_ERROR_CODE, message);
    }

    public DuplicateResourceException(IErrorCode iErrorCode) {
        super(iErrorCode);
    }

    public DuplicateResourceException(IErrorCode iErrorCode, String message) {
        super(iErrorCode, message);
    }

    public DuplicateResourceException(String code, String message) {
        super(code, message, STATUS_CODE);
    }
}
