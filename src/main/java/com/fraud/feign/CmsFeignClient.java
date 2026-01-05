package com.fraud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cms", url = "http://localhost:9092")
public interface CmsFeignClient {
    @GetMapping("transaction-controller/find-transaction-card/{cardId}")
    ResponseEntity<Integer> findTransactionCountByCard(@PathVariable UUID cardId);

}
