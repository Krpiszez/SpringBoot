package com.tpe.main;

import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SmsService;
import com.tpe.service.WhatsAppService;

public class MyApplication {

    public static void main(String[] args) {

        //Create object from Message class(domain package) and setting message
        //========== Level 1 starts ==============
        Message message = new Message();
        message.setMessage("Orders have been submitted to cargo..");

        //Create object from Mail Service(service package)
//        MailService mailService = new MailService();
//
//        mailService.sendMessage(message);
//
//        //Create object from Sms Service(service package)
//        SmsService smsService = new SmsService();
//
//        smsService.sendMessage(message);
//
//        //Create object from WhatsApp Service(service package)
//        WhatsAppService whatsAppService = new WhatsAppService();
//
//        whatsAppService.sendMessage(message);

        //========== Level 1 ends ==============
        // After Level 2 we created an interface and all service classes implemented it so with same data type by changing
        //constructor we can make the method implemented however we want by using method overriding.
        //========== Level 2 starts ==============
//        MessageService mailService = new MailService();
//        mailService.sendMessage(message);
//
//        MessageService whatService = new WhatsAppService();
//        whatService.sendMessage(message);
//
//        MessageService smsService = new SmsService();
//        smsService.sendMessage(message);
        //========== Level 2 ends ==============

        //========== Level 3 starts ==============
        // In level 3 we are minimizing the Object creation. In programming we want our application fast and atomic so we
        // dont want to create Objects for everything we just gonna create and Object when we need it. In the example below
        // is a way to create Object depending on our need, and Spring makes this easier for us it would be like that if there
        // was not Spring.
        String serviceName = "mailService";

        MessageService service;

        if (args.length>0){
            serviceName = args[0];
        }

        if (serviceName.equalsIgnoreCase("whatsAppService")){
            service = new WhatsAppService();
        } else if (serviceName.equalsIgnoreCase("smsService")) {
            service = new SmsService();
        } else {
            service = new MailService();
        }

        service.sendMessage(message);


    }
}
