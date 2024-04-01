package com.shn.payment.controller;

import com.shn.payment.dto.PaymentRequest;
import com.shn.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> pay(@RequestBody PaymentRequest paymentRequest){
        paymentService.pay(paymentRequest);
        return new ResponseEntity<>("Payment has been successfully completed.", HttpStatus.CREATED);
    }
}
