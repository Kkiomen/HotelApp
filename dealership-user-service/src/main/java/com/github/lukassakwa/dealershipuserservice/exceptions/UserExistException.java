package com.github.lukassakwa.dealershipuserservice.exceptions;

public class UserExistException extends Exception {
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }
}
