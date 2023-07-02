package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mailService") // by adding this parameter instance of this class will be created and waiting in IoC(inversion of control) Container
// by this annotation we make this class a component(bilesen) of our Spring Boot so objects will be created here by Spring
//when we ask.?
//@Scope(value = "singleton")// here this is created by default which works on one object for different results ?
@Scope(value = "prototype")// here this is created by developer whenever this Scope is assigned as prototype for every different tasks
// new instance will be created other than singleton.
public class MailService implements MessageService{

    @Value("${app.email}")// Here this annotation lets us use the data we have in properties file which we created in resources.
    // there is a key-value relation in this data, app.email is our key and by @Value we are getting its value in our properties file.
    private String email;

    //Repository repository = new FileRepository(); // here is the usual way of creating object but we make spring handle that.

    // THERE ARE 3 TYPES OF INJECTION their functionality is same but in different ways..
    // 1-) Field Injection this is before variable
    // 2-) Setter Injection this is before setter method
    // 3-) Constructor Injection this is before and within? constructor

    // 1-) FIELD INJECTION
//    @Autowired // to let spring handle object control we need this annotation which we created context in another class and this annotation
//    //uses is. Because for Repository we have @Component. Autowiring means beans are created and waiting for command, this is one way to
//    // call them by injecting this.
//    @Qualifier("dbRepository") // Here we use this @Qualifier annotation because there might be more than 1 class which implements the
//    // interface we use to inject spring so to decide which one will be used to create object we add them name inside @Component() parenthesis
//    // then we use @Qualifier to select which will be used. @Qualifier("dbRepository") is used after @Autowired if there is more than one
//    // concrete child classes.
//    private Repository repository;

    // 2-) SETTER INJECTION
//    private Repository repository;
//    @Autowired
//    @Qualifier("dbRepository")
//    public void setRepository(Repository repository){
//        this.repository = repository;
//    }

    // 3-) CONSTRUCTOR INJECTION this is a little more used because of security and non-exception structures(more closed to mistakes)

    private Repository repository;
    @Autowired
    public MailService(@Qualifier("dbRepository") Repository repository) {
        this.repository = repository;
    }

    public void sendMessage(Message message){
        System.out.println("I am a Mail Service, and I am sending you this message: " + message.getMessage());

        repository.saveMessage(message);

        System.out.println(email);// coming from properties file.

    }
}
