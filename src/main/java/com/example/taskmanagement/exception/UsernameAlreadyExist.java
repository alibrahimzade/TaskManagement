package com.example.taskmanagement.exception;

public class UsernameAlreadyExist extends RuntimeException {
    public UsernameAlreadyExist(String message) {
        super(message);
    }
}
