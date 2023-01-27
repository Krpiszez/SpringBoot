package com.tpe.main;

import com.tpe.domain.Message;
import com.tpe.service.MailService;

public class MyApplication {

    public static void main(String[] args) {

        //Create object from Message class(domain package)
        Message message = new Message();
        message.setMessage("Orders have been submitted to cargo..");

        //Create object from Mail Servide(service package)
        MailService mailService = new MailService();
        mailService.sendMessage(message);


    }
}
