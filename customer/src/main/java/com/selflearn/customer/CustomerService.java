package com.selflearn.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

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
        customerRepository.saveAndFlush(customer);
        // todo: check if fraudster
//        String fraudCheckUrl = "http://localhost:8081/api/v1/fraud-check/{customerId}";
        // service name FRAUD will be resolved as IP address
        String fraudCheckUrl = "http://FRAUD/api/v1/fraud-check/{customerId}";
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                fraudCheckUrl,
                FraudCheckResponse.class,
                customer.getId()
        );

        assert fraudCheckResponse != null;
        if(fraudCheckResponse.isFraudster())
            throw new IllegalStateException("fraudster");
        // todo: send notification
    }
}
