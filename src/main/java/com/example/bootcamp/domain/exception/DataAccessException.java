package com.example.bootcamp.domain.exception;

import com.example.bootcamp.domain.exception.error.CommonErrorCode;
import com.example.bootcamp.domain.exception.error.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder

public class DataAccessException extends BusinessException{

    private static final IErrorCode DEFAULT_ERROR_CODE = CommonErrorCode.DATA_ACCESS_ERROR;
    private static final String DEFAULT_CODE = DEFAULT_ERROR_CODE.getCode();
    private static final int STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public DataAccessException(String message) {
        super(DEFAULT_ERROR_CODE, message);
    }

    public DataAccessException(IErrorCode iErrorCode) {
        super(iErrorCode);
    }

    public DataAccessException(IErrorCode iErrorCode, String message) {
        super(iErrorCode, message);
    }

    public DataAccessException(String code, String message) {
        super(code, message, STATUS_CODE);
    }
}
