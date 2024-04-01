package com.shn.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shn.commonservice.messaging.SendEmailRequest;

import com.shn.notification.dto.MyMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void sendEmail(Message message) {
        try {
            // Mesajı byte dizisinden stringe çevirme
            String jsonMessage = new String(message.getBody());

            // JSON stringini MyMessageClass'a çevirme
            MyMessage myMessage = convertJsonToMyMessage(jsonMessage);

            // Email ve Message'i al
            String email = myMessage.getEmailAddress();
            String messageContent = myMessage.getMessage();

            SimpleMailMessage message1 = new SimpleMailMessage();
            message1.setTo(email);
            message1.setSubject(messageContent);
            message1.setText(messageContent);

            javaMailSender.send(message1);

        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }




    }
    private MyMessage convertJsonToMyMessage(String json) throws Exception {
        // JSON stringini MyMessageClass'a çevirme işlemi
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, MyMessage.class);
    }
}
