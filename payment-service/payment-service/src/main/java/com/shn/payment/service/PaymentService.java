package com.shn.payment.service;

import com.shn.payment.dto.PaymentRequest;
import com.shn.payment.model.Payment;
import com.shn.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void pay(PaymentRequest paymentRequest) {
        Payment payment = Payment.builder()
                .userId(paymentRequest.getUserId())
                .vehicleId(paymentRequest.getVehicleId())
                .totalPrice(paymentRequest.getTotalPrice())
                .reservationId(paymentRequest.getReservationId())
                .build();
        paymentRepository.save(payment);


    }
}
