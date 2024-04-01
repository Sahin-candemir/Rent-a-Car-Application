package com.shn.reservation.dto.controller;

import com.shn.reservation.dto.dto.ReservationRequest;
import com.shn.reservation.dto.dto.ReservationResponse;
import com.shn.reservation.dto.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest){
        return new ResponseEntity<>(reservationService.createReservation(reservationRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservationStatus(@PathVariable Long id){
        reservationService.updateReservationStatus(id);
        return new ResponseEntity<>("Reservation updated success.", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations(){
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getReservationById(id), HttpStatus.OK);
    }
}
