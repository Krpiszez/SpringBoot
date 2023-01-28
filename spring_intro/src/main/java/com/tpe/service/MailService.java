package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component // by this annotation we make this class a component(bilesen) of our Spring Boot so objects will be created here by Spring
//when we ask to.?
public class MailService {

    public void sendMessage(Message message){
        System.out.println("I am a Mail Service, and I am sending you this message: " + message.getMessage());
    }
}
