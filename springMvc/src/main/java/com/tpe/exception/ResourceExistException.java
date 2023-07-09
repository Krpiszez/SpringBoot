package com.tpe.exception;

public class ResourceExistException extends RuntimeException{

    public ResourceExistException(String message) {
        super("Resource already exists!");
    }
}
