package com.example.productservice;

public class InvalidTokenException extends Exception{

    private String message;

    public InvalidTokenException(String message) {
        this.message = message;
    }
}
