package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.domain.User;
import com.tpe.dto.request.CustomerRequest;
import com.tpe.dto.response.CustomerResponse;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    public void saveCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        if (customerRepository.existsByEmail(customerRequest.getEmail()) || customerRepository.existsByUser(userService.findUserByName(customerRequest.getUserName()))){
            throw new ConflictException("Customer with email: " + customerRequest.getEmail() + " already exists!");
        }
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setLastName(customerRequest.getLastName());
        User user = userService.findUserByName(customerRequest.getUserName());
        customer.setUser(user);
        customerRepository.save(customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponse> customerResponseList = new ArrayList<>();

        for (Customer c: customerList){
            CustomerResponse customerResponse =
                    new CustomerResponse(c.getEmail(), c.getName(), c.getLastName(), c.getPhone(), c.getUser().getUserName());
            customerResponseList.add(customerResponse);
        }

        return customerResponseList;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id: " + id+ " can not be found!"));
        return customer;
    }

    public CustomerResponse getCustomerResponseById(Long id) {
        Customer customer = getCustomerById(id);
        CustomerResponse customerResponse =
                new CustomerResponse(
                        customer.getName(),
                        customer.getLastName(),
                        customer.getEmail(), customer.getPhone(), customer.getUser().getUserName());

        return customerResponse;
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}
