package com.shn.vehicle.service;

import com.shn.vehicle.dto.CarResponse;
import com.shn.vehicle.dto.CreateCarRequest;
import com.shn.vehicle.dto.UpdateCarRequest;
import com.shn.vehicle.exception.ResourceNotFoundException;
import com.shn.vehicle.exception.VehicleInUseException;
import com.shn.vehicle.model.Car;
import com.shn.vehicle.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.zip.Deflater;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper mapper;
    public void createCar(CreateCarRequest createCarRequest){
            Car car = Car.builder()
                    .brand(createCarRequest.getBrand())
                    .model(createCarRequest.getModel())
                    .year(createCarRequest.getYear())
                    .color(createCarRequest.getColor())
                    .available(true)
                    .image(createCarRequest.getImage())
                    .dailyPrice(createCarRequest.getDailyPrice())
                    .fuelType(createCarRequest.getFuelType())
                    .build();
            Car saveCar = carRepository.save(car);
    }
    public void updateImage(Long id,MultipartFile file){
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found with id: "+ id));
        try {
            if (!file.getContentType().equals("image/png")) {
                throw new RuntimeException("Hata: Desteklenmeyen dosya türü, sadece PNG dosyaları kabul edilir.");
            }
            car.setImage(file.getBytes());
            carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(car -> mapper.map(car, CarResponse.class)).collect(Collectors.toList());
    }

    public List<CarResponse> getCarsByBrand(String brand) {
        List<Car> cars = carRepository.findAllByBrand(brand);
        return cars.stream().map(car -> mapper.map(car, CarResponse.class)).collect(Collectors.toList());
    }

    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found with id: "+ id));
        return mapper.map(car, CarResponse.class);
    }

    public CarResponse updateCar(Long id, UpdateCarRequest updateCarRequest) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: "+ id));
        car.setColor(updateCarRequest.getColor());
        car.setDailyPrice(updateCarRequest.getDailyPrice());
        car.setDailyPrice(updateCarRequest.getDailyPrice());
        car.setAvailable(updateCarRequest.isAvailable());
        Car saveCar = carRepository.save(car);

        return mapper.map(saveCar, CarResponse.class);
    }

    public CarResponse updateCarAvailable(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: "+ id));
        if(car.isAvailable()){
            car.setAvailable(false);
            Car saveCar = carRepository.save(car);
            return mapper.map(saveCar, CarResponse.class);
        }
        else{
            throw new VehicleInUseException("The vehicle is currently use.");
        }
    }

    //Aracin var mi yok mu kontrol eder, kirada degilse mevcut durumunu gunceller
    public CarResponse carRental(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: "+ id));
        if(car.isAvailable()){
            car.setAvailable(false);
            Car saveCar = carRepository.save(car);
            return mapper.map(saveCar, CarResponse.class);
        }
        else{
            throw new VehicleInUseException("The vehicle is currently use.");
        }
    }

    //kiralanan Aracin iadesini gerceklestirir
    public CarResponse carReturns(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: "+ id));
        if (!car.isAvailable()){
            car.setAvailable(true);
            Car saveCar = carRepository.save(car);

            return mapper.map(saveCar, CarResponse.class);
        }
        else{
            throw new VehicleInUseException("The vehicle is already available");
        }
    }

    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];

        while(!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp,0, size);
        }

        outputStream.close();

        return outputStream.toByteArray();
    }

}
