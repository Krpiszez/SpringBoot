package com.tpe.controller;

import com.tpe.dto.UserRequest;
import com.tpe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@RequestMapping("/user") // here request mapping is no needed because there will be post user method only.
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping

    @RequestMapping("/register") // you can use @RequestMapping here because here there will only be 1 end point

    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequest userRequest){
        String message = "User has been created successfully!";
        userService.saveUser(userRequest);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


}
