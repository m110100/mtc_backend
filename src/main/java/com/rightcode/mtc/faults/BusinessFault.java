package com.rightcode.mtc.faults;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessFault extends RuntimeException {
    private String message;
    private String code;

    public BusinessFault(String message, String code) {
        super(message);

        this.message = message;
        this.code = code;
    }

    public BusinessFault(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessFault(Throwable cause) {
        super(cause);
    }

    public BusinessFault(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
