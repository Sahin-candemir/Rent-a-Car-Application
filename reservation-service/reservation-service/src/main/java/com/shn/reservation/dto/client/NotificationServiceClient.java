package com.shn.reservation.dto.client;

import com.shn.commonservice.messaging.SendEmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "notification-service", path = "/api/notification/")
public interface NotificationServiceClient {

    @PostMapping
    ResponseEntity<String> sendEmail(@RequestBody SendEmailRequest sendEmailRequest);
}
