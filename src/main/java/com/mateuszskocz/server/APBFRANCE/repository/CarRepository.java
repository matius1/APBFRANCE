package com.mateuszskocz.server.APBFRANCE.repository;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Mati on 08.07.2017.
 */
public interface CarRepository extends MongoRepository<Car, String> {

    List<Car> findById(String id);

}
