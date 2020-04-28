package com.margin.error;


import com.margin.enums.LoadingStage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiException extends RuntimeException{

    private final MarginApiErrorType errorType;

    private LoadingStage stage;

    private final int responseStatusCode;

    private final int applicationErrorCode;

    private final String message;

    public ApiException(String message) {
        this.errorType = null;
        this.responseStatusCode = 0;
        this.applicationErrorCode = 0;
        this.message = message;
    }

    public ApiException(String message, LoadingStage stage) {
        this.stage = stage;
        this.message = message;
        this.errorType = null;
        this.responseStatusCode = 0;
        this.applicationErrorCode = 0;
    }
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
}