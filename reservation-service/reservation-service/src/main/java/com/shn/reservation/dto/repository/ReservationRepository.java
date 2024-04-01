package com.shn.reservation.dto.repository;

import com.shn.reservation.dto.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
