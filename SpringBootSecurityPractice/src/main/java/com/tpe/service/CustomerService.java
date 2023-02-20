package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.dto.request.CustomerRequest;
import com.tpe.exception.ConflictException;
import com.tpe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    public void saveCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        if (customerRepository.existsByEmail(customerRequest.getEmail())){
            throw new ConflictException("Customer with email: " + customerRequest.getEmail() + " already exists!");
        }
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setLastName(customerRequest.getLastName());
        customer.setUser(userService.findUserByName(customerRequest.getUserName()));
        customerRepository.save(customer);
    }
}
