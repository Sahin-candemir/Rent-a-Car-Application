package com.shn.reservation.dto.dto;

import com.shn.reservation.dto.model.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

    private Long userId;
    private Long vehicleId;

    private LocalDate startDate;
    private LocalDate endDate;

    private ReservationStatus status;

}
