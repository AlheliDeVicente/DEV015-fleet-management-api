package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralExceptions extends  RuntimeException {
    public GeneralExceptions() {
    }

    public GeneralExceptions(String message) {
        super(message);
    }

    public GeneralExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralExceptions(Throwable cause) {
        super(cause);
    }

    public GeneralExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
