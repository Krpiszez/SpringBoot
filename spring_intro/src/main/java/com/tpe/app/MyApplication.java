package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;

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
        MessageService service2 = context.getBean("mailService", MessageService.class); // here if we create another variable from same place
        // it is not going to create another object both will refer to same object in memory so this will not consume any space in memory. It is
        // coming from being Singleton. @Scope(value="singleton") == this is default for our container in Spring. Check MailService class.
        System.out.println(service2==service);// it prints false when we use @Scope(value = "prototype")

        // Call Point from Container

        Point point = context.getBean("point", Point.class);
        System.out.println("X coordinate: " + point.getX());

        context.close();// Life cycle of created beans will end


    }

}
