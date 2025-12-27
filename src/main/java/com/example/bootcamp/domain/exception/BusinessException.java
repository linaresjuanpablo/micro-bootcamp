package com.example.bootcamp.domain.exception;

import com.example.bootcamp.domain.exception.error.CommonErrorCode;
import com.example.bootcamp.domain.exception.error.ErrorCodeRegistry;
import com.example.bootcamp.domain.exception.error.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BusinessException extends RuntimeException{
    private IErrorCode IErrorCode;
    private String code;
    private String message;
    private int statusCode;


    public BusinessException (IErrorCode iErrorCode){
        super(iErrorCode.getMessage());
        this.IErrorCode = IErrorCode;
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
        this.statusCode = iErrorCode.getStatusCode();
    }
    public BusinessException(IErrorCode iErrorCode, String message){
        super(message);
        this.IErrorCode = IErrorCode;
        this.code = iErrorCode.getCode();
        this.message = message;
        this.statusCode = iErrorCode.getStatusCode();
    }
    public BusinessException(String code, String message, int statusCode){
        super(message);
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
        this.IErrorCode = ErrorCodeRegistry.getOrDefault(code, CommonErrorCode.INTERNAL_ERROR);


    }




}
