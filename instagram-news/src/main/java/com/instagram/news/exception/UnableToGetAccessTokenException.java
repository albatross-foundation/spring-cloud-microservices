package com.instagram.news.exception;

public class UnableToGetAccessTokenException extends RuntimeException {
    public UnableToGetAccessTokenException(String message) {
        super(message);
    }
}