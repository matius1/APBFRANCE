package com.mateuszskocz.server.APBFRANCE.comparator;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.pageReader.ApbFrancePageReader;
import com.mateuszskocz.server.APBFRANCE.pageReader.otoMotoPageReader;
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
public class ApbComparator implements ComparatorInterface, ApbInterface {

    private final CarService carService;
    private final ApbFrancePageReader apbFrancePageReader;
    private final otoMotoPageReader otoMotoPageReader;

    @Autowired
    public ApbComparator(CarService carService, ApbFrancePageReader apbFrancePageReader, com.mateuszskocz.server.APBFRANCE.pageReader.otoMotoPageReader otoMotoPageReader) {
        this.carService = carService;
        this.apbFrancePageReader = apbFrancePageReader;
        this.otoMotoPageReader = otoMotoPageReader;
    }

    @Override
    public Boolean checkInDB(String id) {
        Car result = carService.fingById(id);
        if (result == null)
            return false;

        return !result.getModel().equals(null);
    }

    @Override
    public List<Car> checkAPBNewCars() {

        int newCars = 0;
        List<Car> carList = new ArrayList<>();

        List<Car> carListUsed = ApbFrancePageReader.getCarsUsed();
        for (Car car : carListUsed) {
            if (!checkInDB(car.getId())) {
                carService.create(car);
                newCars++;
                carList.add(car);
            }
        }

        List<Car> carListDamaged = ApbFrancePageReader.getCarsDamaged();
        for (Car car : carListDamaged) {
            if (!checkInDB(car.getId())) {
                carService.create(car);
                newCars++;
                carList.add(car);
            }
        }

        System.out.println("OTOMOTO apbcomparator: ");
        List<Car> carListOtoMoto = otoMotoPageReader.getCarsUsed();
        for (Car car : carListOtoMoto) {
            if (!checkInDB(car.getId())) {
                carService.create(car);
                newCars++;
                carList.add(car);
            }
        }

        return carList;
    }


}
