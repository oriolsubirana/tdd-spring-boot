package com.example.demo.tdd.service;

import com.example.demo.tdd.domain.Car;
import com.example.demo.tdd.exception.CarNotFoundException;
import com.example.demo.tdd.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCarDetails(String name) {
        Car car = carRepository.findByName(name);
        if (car == null) {
            throw new CarNotFoundException();
        }
        return car;
    }
}
