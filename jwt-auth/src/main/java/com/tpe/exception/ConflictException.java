package com.tpe.exception;

import org.aspectj.bridge.Message;

public class ConflictException extends RuntimeException{

    public ConflictException(String message){
        super(message);
    }

}
