package com.shn.vehicle.controller;

import com.shn.vehicle.dto.CarResponse;
import com.shn.vehicle.dto.CreateCarRequest;
import com.shn.vehicle.dto.UpdateCarRequest;
import com.shn.vehicle.model.Car;
import com.shn.vehicle.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CreateCarRequest createCarRequest){
        carService.createCar(createCarRequest);
        return new ResponseEntity<>("Create Car Success", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<?>> getAllCars(){
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
    }
    @GetMapping("/getByBrand")
    public ResponseEntity<List<CarResponse>> getCarsByBrand(@RequestParam String brand){
        return new ResponseEntity<>(carService.getCarsByBrand(brand), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCarById(@PathVariable Long id,@RequestBody UpdateCarRequest updateCarRequest){
        return new ResponseEntity<>(carService.updateCar(id, updateCarRequest), HttpStatus.OK);
    }
    @PutMapping("/updateImage/{id}")
    public ResponseEntity<String> updateImage(@PathVariable Long id,@RequestParam("file") MultipartFile file){
        carService.updateImage(id,file);
        return new ResponseEntity<>("Image updated success", HttpStatus.CREATED);
    }
    @PutMapping("/updateAvailable/{id}")
    public ResponseEntity<CarResponse> updateCarAvailable(@PathVariable Long id){
        return new ResponseEntity<>(carService.updateCarAvailable(id), HttpStatus.OK);
    }
    @PutMapping("/rental/{id}")
    public ResponseEntity<CarResponse> carRental(@PathVariable Long id){
        return new ResponseEntity<>(carService.carRental(id), HttpStatus.OK);
    }
    @PutMapping("/returns/{id}")
    public ResponseEntity<CarResponse> carReturns(@PathVariable Long id){
        return new ResponseEntity<>(carService.carReturns(id), HttpStatus.OK);
    }
}
