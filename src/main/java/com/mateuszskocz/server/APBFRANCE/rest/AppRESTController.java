package com.mateuszskocz.server.APBFRANCE.rest;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.pageReader.ApbFrancePageReader;
import com.mateuszskocz.server.APBFRANCE.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mati on 08.07.2017.
 */
@RestController
@RequestMapping("/apb/car")
public class AppRESTController {

    private final CarService carService;

    private final ApbFrancePageReader apbFrancePageReader;

    @Autowired
    public AppRESTController(CarService carService, ApbFrancePageReader apbFrancePageReader) {
        this.carService = carService;
        this.apbFrancePageReader = apbFrancePageReader;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCars")
    public @ResponseBody
    List<Car> findAll() {

        return carService.getObj();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCarById/{id}")
    public @ResponseBody
    Car findById(@PathVariable String id) {

        return carService.fingById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public @ResponseBody
    String test1() {

        return "TEST";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readAPB/used")
    public @ResponseBody
    List<Car> readAPBUsedCars() {

        return apbFrancePageReader.getCarsUsed();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveCars/used")
    public @ResponseBody
    List<Car> saveUsedCars() {

        List<Car> carList = apbFrancePageReader.getCarsUsed();
        carList.forEach(carService::create);

        return carList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readAPB/damaged")
    public @ResponseBody
    List<Car> readAPBDamagedCars() {

        return apbFrancePageReader.getCarsDamaged();
    }


}
