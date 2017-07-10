package com.mateuszskocz.server.APBFRANCE.comparator;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.pageReader.ApbFrancePageReader;
import com.mateuszskocz.server.APBFRANCE.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mati on 10.07.2017.
 */
@Service
public class ApbComparator implements ComparatorInterface {

    private final CarService carService;
    private final ApbFrancePageReader apbFrancePageReader;


    @Autowired
    public ApbComparator(CarService carService, ApbFrancePageReader apbFrancePageReader) {
        this.carService = carService;
        this.apbFrancePageReader = apbFrancePageReader;
    }


    @Override
    public Boolean checkInDB(String id) {
        Car result = carService.fingById(id);
        if (result == null)
            return false;

        if (result.getModel().equals(null))
            return false;
        else
            return true;
    }

    public List<Car> checkAPBNewCars() {

        int newCars = 0;
        List<Car> carList = new ArrayList<>();
        List<Car> carListUsed = apbFrancePageReader.getCarsUsed();

        for (Car car : carListUsed) {
            if (!checkInDB(car.getId())) {
                carService.create(car);
                System.out.println("Nowe Auto: " + car);
                newCars++;
                carList.add(car);
            }
        }

        List<Car> carListDamaged = apbFrancePageReader.getCarsDamaged();

        for (Car car : carListDamaged) {
            if (!checkInDB(car.getId())) {
                carService.create(car);
                System.out.println("Nowe Auto: " + car);
                newCars++;
                carList.add(car);

            }
        }

        return carList;
    }

}
