package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplication {

    public static void main(String[] args) {
        Message message = new Message();
        message.setMessage("Your orders have been received...");

        // indicating configuration class. Here with this class we call our Configurated class ?
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        // Here we are not creating object but thanks to bean it uses the elements of class by creating object in bean ?
        MailService service = context.getBean(MailService.class);
        service.sendMessage(message);

    }

}
