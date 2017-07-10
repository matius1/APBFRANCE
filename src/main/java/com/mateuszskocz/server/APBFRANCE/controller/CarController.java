package com.mateuszskocz.server.APBFRANCE.controller;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by Mati on 08.07.2017.
 */
@Controller
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    public void create(Car carEntity) {
        carService.create(carEntity);
        System.out.println("Car created successfully");
    }

}
