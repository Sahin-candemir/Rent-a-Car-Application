package com.shn.vehicle.service;

import com.shn.vehicle.dto.CarResponse;
import com.shn.vehicle.dto.CreateCarRequest;
import com.shn.vehicle.model.Car;
import com.shn.vehicle.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarRepository carRepository;
    private ModelMapper mapper;
    private CarService carService;

    @BeforeEach
    public void setup(){
        carRepository = Mockito.mock(CarRepository.class);
        mapper = Mockito.mock(ModelMapper.class);

        carService = new CarService(carRepository,mapper);
    }

    @Test
    void createCar_shouldReturnCarResponse() {
        Long carId = 1L;
        CreateCarRequest createCarRequest = CreateCarRequest.builder()
                .brand("bmw")
                .model("a1")
                .year(2005)
                .color("black")
                .dailyPrice(new BigDecimal(1000))
                .fuelType("diesel")
                .build();

        CarResponse carResponse = CarResponse.builder()
                .id(carId)
                .brand("bmw")
                .model("a1")
                .year(2005)
                .color("black")
                .dailyPrice(new BigDecimal(1000))
                .fuelType("diesel")
                .available(true)
                .build();
        Car car = Car.builder()
                .id(carId)
                .brand("bmw")
                .model("a1")
                .year(2005)
                .color("black")
                .dailyPrice(new BigDecimal(1000))
                .fuelType("diesel")
                .available(true)
                .build();
        Mockito.when(carRepository.save(Mockito.any(Car.class))).thenReturn(car);
        Mockito.when(mapper.map(car, CarResponse.class)).thenReturn(carResponse);

        //CarResponse result = carService.createCar(createCarRequest);

        //assertEquals(result, carResponse);
    }
/*
    @Test
    void getAllCars_shouldReturnCarResponseList(){
        List<Car> cars = new Arrays.asList(
                new Car(1L,"bmw","a1",2005,"black","diesel",new BigDecimal(1000),true),
                new Car(2L,"bmw","a1",2005,"black","diesel",new BigDecimal(1000),true));
        List<CarResponse> carResponseList = new ArrayList<>
                (Collections.singletonList(new CarResponse(1L,"bmw","a1",2005,"black","diesel",new BigDecimal(1000),true)));
        Mockito.when(carRepository.findAll()).thenReturn(cars);
        Mockito.when(mapper.map(Mockito.any(), eq(CarResponse.class))).thenReturn(carResponseList.get(0)).thenReturn(carResponseList.get(1));

        List<CarResponse> result = carService.getAllCars();

        assertEquals(result, carResponseList);

    }

 */
}