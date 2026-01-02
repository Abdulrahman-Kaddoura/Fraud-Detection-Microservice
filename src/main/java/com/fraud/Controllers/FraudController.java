package com.fraud.Controllers;

import com.fraud.DTOs.CheckFraudDTO;
import com.fraud.Services.FraudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fraud-controller")
public class FraudController {
    @Autowired
    FraudService fraudService;

    @PostMapping("/check-fraud")
    public ResponseEntity<Boolean> checkFraud(@RequestBody CheckFraudDTO checkFraudDTO) {
        return new ResponseEntity<>(fraudService.checkFraud(checkFraudDTO.getTransactionAmount(), checkFraudDTO.getTransactionId(), checkFraudDTO.getCardId()), HttpStatus.ACCEPTED);
    }
}
