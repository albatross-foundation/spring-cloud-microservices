package com.instagram.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String userName, String resource, String operation) {
        super(String.format("User %s is not allowed to %s resource %s", userName, operation, resource));
    }
}
