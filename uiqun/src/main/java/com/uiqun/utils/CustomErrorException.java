package com.uiqun.utils;

public class CustomErrorException extends RuntimeException {

    public CustomErrorException(String message) {
        super(message);
    }

    public CustomErrorException() {
    }
}
