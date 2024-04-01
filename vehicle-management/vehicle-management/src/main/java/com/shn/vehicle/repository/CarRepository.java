package com.shn.vehicle.repository;

import com.shn.vehicle.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByBrand(String brand);
}
