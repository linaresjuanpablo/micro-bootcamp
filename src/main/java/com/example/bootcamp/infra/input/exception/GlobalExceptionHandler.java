package com.example.bootcamp.infra.input.exception;

import com.example.bootcamp.domain.exception.BusinessException;
import com.example.bootcamp.domain.exception.DataAccessException;
import com.example.bootcamp.domain.exception.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.bootcamp.domain.exception.error.IErrorCode;
import com.example.bootcamp.domain.exception.error.CommonErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static com.example.bootcamp.infra.util.ConstantsConfiguration.*;

@Configuration
@Order(-2)
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice

public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ErrorResponse errorResponse;

        if (ex instanceof BusinessException businessException){
            exchange.getResponse().setStatusCode(HttpStatus.valueOf(businessException.getStatusCode()));
            errorResponse = new ErrorResponse(businessException.getCode(), businessException.getMessage());
        } else if (ex instanceof WebExchangeBindException bindException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);

            String errorMessages = bindException.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining("; "));

            errorResponse = new ErrorResponse(VALIDATION_ERROR_CODE, errorMessages);
        } else if (ex instanceof ResponseStatusException responseStatusException) {
            exchange.getResponse().setStatusCode(responseStatusException.getStatusCode());

            String code = "ERROR_" + responseStatusException.getStatusCode().value();
            String message = responseStatusException.getReason() != null ?
                    responseStatusException.getReason() : responseStatusException.getMessage();

            errorResponse =  new ErrorResponse(code, message);
        }else {
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

            IErrorCode iErrorCode = CommonErrorCode.INTERNAL_ERROR;
            errorResponse = new ErrorResponse(
                    iErrorCode.getCode(),
                    INTERNAL_ERROR_PREFIX + ex.getMessage()
            );
        }
        DataBuffer dataBuffer;
        try {
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(errorResponse));
        }catch (JsonProcessingException e){
            log.error(ERROR_WRITING_JSON, e);
            dataBuffer = bufferFactory.wrap(ERROR_PROCESSING_RESPONSE.getBytes());
        }


        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
    @ExceptionHandler(ValidationException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationException(ValidationException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse));

    }
    @ExceptionHandler(DataAccessException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleDataAccessException (DataAccessException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }
}
