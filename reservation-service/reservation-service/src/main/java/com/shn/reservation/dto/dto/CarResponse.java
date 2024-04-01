package com.shn.reservation.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;

    private String brand;
    private String model;
    private int year;
    private String color;
    private String fuelType;
    private BigDecimal dailyPrice;
    private boolean available;
}
