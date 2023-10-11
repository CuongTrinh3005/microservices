package com.selflearn.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public void register(CustomerRegisterRequest customerRegisterRequest){
        // apply builder pattern
        Customer customer = Customer.builder()
                .firstName(customerRegisterRequest.firstName())
                .lastName(customerRegisterRequest.lastName())
                .email(customerRegisterRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.save(customer);
    }
}
