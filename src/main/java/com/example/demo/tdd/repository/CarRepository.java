package com.example.demo.tdd.repository;

import com.example.demo.tdd.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Car findByName(String name);
}