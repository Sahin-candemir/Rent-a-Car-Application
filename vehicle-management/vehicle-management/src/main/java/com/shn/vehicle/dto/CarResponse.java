package com.shn.vehicle.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponse {

    private Long id;

    private String brand;
    private String model;
    private int year;
    private String color;
    private String fuelType;
    private BigDecimal dailyPrice;
    private boolean available;

    private byte[] image;
}
