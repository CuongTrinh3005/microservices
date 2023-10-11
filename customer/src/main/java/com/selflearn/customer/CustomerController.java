package com.selflearn.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    private void register(@RequestBody CustomerRegisterRequest customerRegisterRequest){
        log.info("New customer registration {}", customerRegisterRequest);
        customerService.register(customerRegisterRequest);
    }

    @GetMapping
    private List<Customer> getAll(){
        return customerService.getAll();
    }
}
