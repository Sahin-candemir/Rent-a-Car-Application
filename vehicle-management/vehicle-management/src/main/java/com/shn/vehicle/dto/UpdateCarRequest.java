package com.shn.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

    private String color;
    private String fuelType;
    private BigDecimal dailyPrice;
    private boolean available;
}
