package com.shn.reservation.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    private Long userId;
    private Long vehicleId;

    private LocalDate startDate;
    private LocalDate endDate;
}
