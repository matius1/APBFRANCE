package com.mateuszskocz.server.APBFRANCE.service;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Mati on 08.07.2017.
 */
@Service
public class CarService implements ServiceInterface<Car> {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getObj() {
        List<Car> carList = carRepository.findAll();
        return convertToDTOs(carList);
//        return null;
    }


    private List<Car> convertToDTOs(List<Car> models){
        return models.stream().map(this::convertToDTO).collect(toList());
    }

    private Car convertToDTO(Car model){
        Car dto = new Car();
        dto.setId(model.getId());
        dto.setCena(model.getCena());
        dto.setMarka(model.getMarka());
        dto.setModel(model.getModel());
        dto.setRok(model.getRok());
        dto.setCategory(model.getCategory());
        return dto;
    }



    @Override
    public Car create(Car obj) {

        return carRepository.save(obj);
    }

    @Override
    public Car fingById(String id) {
        if(!carRepository.findById(id).isEmpty())
            return carRepository.findById(id).get(0);
        else
            return null;
    }

    @Override
    public Car update(Car obj) {
        return null;
    }



}
