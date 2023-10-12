package com.selflearn.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
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
