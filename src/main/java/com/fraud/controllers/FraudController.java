package com.fraud.controllers;

import com.fraud.dtos.CheckFraudDTO;
import com.fraud.services.FraudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import static com.fraud.togglz.Features.CHECK_FRAUD;

@RestController
@RequestMapping("fraud-controller")
public class FraudController {
    @Autowired
    FraudService fraudService;

    @Autowired
    FeatureManager featureManager;

    @PostMapping("/check-fraud")
    public ResponseEntity<Boolean> checkFraud(@RequestBody CheckFraudDTO checkFraudDTO) throws Exception {
        if (featureManager.isActive(CHECK_FRAUD)) {
            return new ResponseEntity<>(fraudService.checkFraud(checkFraudDTO.getTransactionAmount(), checkFraudDTO.getTransactionId(), checkFraudDTO.getCardId()), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }
}
