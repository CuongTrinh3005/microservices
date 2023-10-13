package com.selflearn.fraud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudController {
    private final FraudCheckHistoryService fraudCheckHistoryService;

    @Value("${server.port}")
    Integer port;

    public FraudController(FraudCheckHistoryService fraudCheckHistoryService) {
        this.fraudCheckHistoryService = fraudCheckHistoryService;
    }

    @GetMapping("{customerId}")
    private FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        String logInfo = String.format("Fraud check for customer %s at port %s", customerId, port);
        log.info(logInfo);
        return new FraudCheckResponse(fraudCheckHistoryService.isFraudulentCustomer(customerId));
    }
}
