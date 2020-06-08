package com.example.demo.tdd.controller;

import com.example.demo.tdd.controller.CarController;
import com.example.demo.tdd.domain.Car;
import com.example.demo.tdd.exception.CarNotFoundException;
import com.example.demo.tdd.service.CarService;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest implements WithAssertions {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void getCar_shouldReturnCar() throws Exception {
        given(carService.getCarDetails(anyString())).willReturn(new Car("prius", "hybrid"));

        mockMvc.perform(get("/cars/prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prius"))
                .andExpect(jsonPath("type").value("hybrid"));
    }

    @Test
    public void getCar_notFound() throws Exception {
        given(carService.getCarDetails(anyString()))
                .willThrow(new CarNotFoundException());

        mockMvc.perform(get("/cars/prius"))
                .andExpect(status().isNotFound());
    }
}
