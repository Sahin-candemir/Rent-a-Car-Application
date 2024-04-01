package com.shn.notification.controller;

import com.shn.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

  /*  @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailRequest sendEmailRequest){
        notificationService.sendEmail(sendEmailRequest);
        return new ResponseEntity<>("Email meesage was send successfully.", HttpStatus.OK);
    }
    
   */
}
