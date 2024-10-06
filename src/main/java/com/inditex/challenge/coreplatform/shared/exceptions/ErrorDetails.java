package com.inditex.challenge.coreplatform.shared.exceptions;

import lombok.Getter;

@Getter
public abstract class ErrorDetails extends Exception {
    private final int errorCode;

    public ErrorDetails(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
