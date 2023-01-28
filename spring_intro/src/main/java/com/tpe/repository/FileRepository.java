package com.tpe.repository;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component("fileRepository")// here we are adding this class as bean to Spring Container and now its being used by Spring to create Objects etc.
public class FileRepository implements Repository{
    @Override
    public void saveMessage(Message message) {
        System.out.println("Your Message has been saved to file.." + message.getMessage());
    }
}
