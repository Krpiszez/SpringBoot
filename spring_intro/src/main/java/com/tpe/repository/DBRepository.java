package com.tpe.repository;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component("dbRepository")
public class DBRepository implements Repository{
    @Override
    public void saveMessage(Message message) {
        System.out.println("Your Message has been saved to DataBase.." + message.getMessage());
    }
}
