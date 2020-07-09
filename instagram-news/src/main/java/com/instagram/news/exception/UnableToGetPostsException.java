package com.instagram.news.exception;

public class UnableToGetPostsException extends RuntimeException {
    public UnableToGetPostsException(String message) {
        super(message);
    }
}