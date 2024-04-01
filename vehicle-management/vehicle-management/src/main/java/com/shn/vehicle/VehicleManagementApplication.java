package com.shn.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VehicleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleManagementApplication.class, args);
	}

}
