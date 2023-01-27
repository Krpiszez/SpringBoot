package com.tpe.service;

import com.tpe.domain.Message;

public class SmsService implements MessageService{

    public void sendMessage(Message message){
        System.out.println("I am a SMS service. I am sending you this message: " + message.getMessage());
    }

}
