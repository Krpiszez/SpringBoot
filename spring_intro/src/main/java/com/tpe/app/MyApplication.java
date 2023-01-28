package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplication {

    public static void main(String[] args) {
        Message message = new Message();
        message.setMessage("Your orders have been received...");

        // indicating configuration class. Here with this class we call our Configurated class ?
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        // Here we are not creating object but thanks to bean it uses the elements of class by creating object in bean ?

        //We are running mail service class, but after we create interface we can use it to create object - bean ?
        //MailService service = context.getBean(MailService.class);

        //Here we create it from parent interface which is Message Service
        // when we call the interface which has more than one child which has @Component annotation it thros an NoUniqueBeanDefinitionException
        // to solve the problem coming with this exception we type parameter like this getBean("mailService", MessageService.class)
        //Here mailService is coming from child class @Component annotation we add parameter.
        //MessageService service = context.getBean(MessageService.class);
        MessageService service = context.getBean("mailService", MessageService.class); // here the exception is solved by giving components name.
        service.sendMessage(message);

    }

}
