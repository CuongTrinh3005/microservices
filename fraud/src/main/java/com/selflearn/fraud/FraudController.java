package com.selflearn.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping("{customerId}")
    private FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        log.info("Fraud check for customer {} ", customerId);
        return new FraudCheckResponse(fraudCheckHistoryService.isFraudulentCustomer(customerId));
    }
}
