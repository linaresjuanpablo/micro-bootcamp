package com.example.bootcamp.domain.exception.error;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ErrorCodeRegistry {

    private static final Map<String, IErrorCode> errorCodeMap = new HashMap<>();

    static {
        for (CommonErrorCode ierrorCode : CommonErrorCode.values()){
            register(ierrorCode);
        }

    }

    public static void register(IErrorCode errorCode){
        if (errorCodeMap.containsKey(errorCode.getCode())){
            throw new IllegalArgumentException("Error code" + errorCode.getCode() + "is already registered");
        }
        errorCodeMap.put(errorCode.getCode(), errorCode);
    }

    public static Optional<IErrorCode> lookup(String code){
        return Optional.ofNullable(errorCodeMap.get(code));
    }

    public static IErrorCode getOrDefault(String code, IErrorCode defaultErrorCode){
        return lookup(code).orElse(defaultErrorCode);
    }

    public static Map<String, IErrorCode> getAllErrorCodes(){return new HashMap<>(errorCodeMap);}
}
