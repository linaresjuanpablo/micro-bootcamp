package com.example.bootcamp.domain.exception.error;

public interface IErrorCode {
    String getCode();
    String getMessage();
    int getStatusCode();

    ErrorBootcamp getBootcamp();
}
