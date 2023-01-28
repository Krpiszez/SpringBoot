package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("whatsAppService")
public class WhatsAppService implements MessageService{
    @Autowired
    // to let spring handle object control we need this annotation which we created context in another class and this annotation
    //uses is. Because for Repository we have @Component
    @Qualifier("fileRepository")
    private Repository repository;
    @Override
    public void sendMessage(Message message) {
        System.out.println("I am a WhatsApp Service, and I am sending you this message: " + message.getMessage());

        repository.saveMessage(message);
    }
}
