package com.shn.vehicle.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Confi {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
