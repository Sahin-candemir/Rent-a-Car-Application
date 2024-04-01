package com.shn.reservation.dto.client;

import com.shn.reservation.dto.dto.CarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "vehicle-management", path = "/api/car/")
public interface VehicleClient {

    @GetMapping("/{id}")
    ResponseEntity<CarResponse> getCarById(@PathVariable Long id);

    @PutMapping("/updateAvailable/{id}")
    ResponseEntity<CarResponse> updateCarAvailable(@PathVariable Long id);
}
