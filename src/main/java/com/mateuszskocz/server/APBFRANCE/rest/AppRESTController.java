package com.mateuszskocz.server.APBFRANCE.rest;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.domain.Recipient;
import com.mateuszskocz.server.APBFRANCE.pageReader.ApbFrancePageReader;
import com.mateuszskocz.server.APBFRANCE.repository.RecipientRepository;
import com.mateuszskocz.server.APBFRANCE.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mati on 08.07.2017.
 */
@RestController
@RequestMapping("/apb")
public class AppRESTController {

    private final CarService carService;

    private final ApbFrancePageReader apbFrancePageReader;

    private RecipientRepository recipientRepository;

    @Autowired
    public AppRESTController(CarService carService, ApbFrancePageReader apbFrancePageReader, RecipientRepository recipientRepository) {
        this.carService = carService;
        this.apbFrancePageReader = apbFrancePageReader;
        this.recipientRepository = recipientRepository;
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

    @RequestMapping(method = RequestMethod.GET, value = "/emails/add")
    public @ResponseBody
    List<Recipient> addEmail() {
//        Recipient recipient = new Recipient();
//        recipient.setEmail("matius1matius@gmail.com");
//        recipientRepository.save(recipient);
//        recipient.setEmail("wasekbasia@wp.pl");
//        recipientRepository.save(recipient);
        return recipientRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emails/add/b")
    public @ResponseBody
    List<Recipient> addEmailB() {
        Recipient recipient = new Recipient();
        recipient.setEmail("wasekbasia@wp.pl");
        recipientRepository.save(recipient);
        return recipientRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emails/add/m")
    public @ResponseBody
    List<Recipient> addEmailM() {
        Recipient recipient = new Recipient();
        recipient.setEmail("matius1matius@gmail.com");
        recipientRepository.save(recipient);
        return recipientRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emails/del")
    public @ResponseBody
    List<Recipient> deleteEmail() {
        recipientRepository.deleteAll();
        return recipientRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/emails/show")
    public @ResponseBody
    List<Recipient> showEmail() {
        return recipientRepository.findAll();
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
