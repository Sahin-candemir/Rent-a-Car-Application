package com.shn.reservation.dto.client;

import feign.codec.ErrorDecoder;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    ErrorDecoder errorDecoder(){
        return new ErrorMessageDecoder();
    }

    @Bean
    Queue queue(){
        return new Queue(queue);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

   /* @Bean
    public Jackson2JsonMessageConverter producerMessajeConverter(){
        return new Jackson2JsonMessageConverter();
    }
    /*@Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessajeConverter());
        return rabbitTemplate;
    }

     */
}
