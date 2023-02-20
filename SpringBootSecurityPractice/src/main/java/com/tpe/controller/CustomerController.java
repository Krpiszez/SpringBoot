package com.tpe.controller;

import com.tpe.dto.request.CustomerRequest;
import com.tpe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        customerService.saveCustomer(customerRequest);
        String message = "Customer has been saved successfully!";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

}
