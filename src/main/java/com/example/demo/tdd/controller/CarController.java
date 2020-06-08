package com.example.demo.tdd.controller;

import com.example.demo.tdd.domain.Car;
import com.example.demo.tdd.exception.CarNotFoundException;
import com.example.demo.tdd.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/{name}")
    private Car getCar(@PathVariable String name) throws CarNotFoundException {
        return carService.getCarDetails(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void carNotFoundHandler(CarNotFoundException ex) {
    }
}
