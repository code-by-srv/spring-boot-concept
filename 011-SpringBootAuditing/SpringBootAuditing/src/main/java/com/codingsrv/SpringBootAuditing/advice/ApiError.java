package com.codingsrv.SpringBootAuditing.advice;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private String message;

    private LocalDateTime timeStamp;

    private HttpStatus statusCode;

    public ApiError(String message, HttpStatus statusCode) {
        this();
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }



}
