package com.tpe.service;

import com.tpe.domain.Message;

public class MailService implements MessageService{

    public void sendMessage(Message message){
        System.out.println("I am a mail service. I am sending you this message: " + message.getMessage());
    }
}
