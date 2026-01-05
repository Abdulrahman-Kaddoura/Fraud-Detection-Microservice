package com.fraud.services;

import com.fraud.feign.CmsFeignClient;
import com.fraud.models.Fraud;
import com.fraud.repositories.FraudRepository;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class FraudService {

    @Autowired
    FraudRepository fraudRepository;

    @Autowired
    Environment environment;

    @Autowired
    CmsFeignClient cmsFeignClient;

    public boolean checkFraud(BigDecimal transactionAmount, UUID transactionId, UUID cardID) {
        String maxAmount = environment.getProperty("maxAmount");
        if (transactionAmount.intValue() > Integer.valueOf(maxAmount)) {
            Fraud fraud = new Fraud();
            fraud.setTransactionId(transactionId);
            fraud.setReason("Transaction value exceeded limit");
            fraudRepository.save(fraud);
            return true;
        }

        String maxTransactionCount = environment.getProperty("maxTransactionCount");
        Integer count = cmsFeignClient.findTransactionCountByCard(cardID).getBody();
        if (count > Integer.valueOf(maxTransactionCount)) {
            Fraud fraud = new Fraud();
            fraud.setTransactionId(transactionId);
            fraud.setReason("Reached max count of transactions");
            fraudRepository.save(fraud);
            return true;
        }
        return false;
    }
}
