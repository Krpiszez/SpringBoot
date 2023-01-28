package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("mailService") // by adding this parameter instance of this class will be created and waiting in IoC(inversion of control) Container
// by this annotation we make this class a component(bilesen) of our Spring Boot so objects will be created here by Spring
//when we ask.?
public class MailService implements MessageService{

    //Repository repository = new FileRepository(); // here is the usual way of creating object but we make spring handle that.
    @Autowired // to let spring handle object control we need this annotation which we created context in another class and this annotation
    //uses is. Because for Repository we have @Component. Autowiring means beans are created and waiting for command, this is one way to
    // call them by injecting this.
    @Qualifier("dbRepository") // Here we use this @Qualifier annotation because there might be more than 1 class which implements the
    // interface we use to inject spring so to decide which one will be used to create object we add them name inside @Component() parenthesis
    // then we use @Qualifier to select which will be used.
    private Repository repository;
    public void sendMessage(Message message){
        System.out.println("I am a Mail Service, and I am sending you this message: " + message.getMessage());

        repository.saveMessage(message);
    }
}
