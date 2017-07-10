package com.mateuszskocz.server.APBFRANCE;

import com.mateuszskocz.server.APBFRANCE.comparator.ApbComparator;
import com.mateuszskocz.server.APBFRANCE.comparator.ComparatorInterface;
import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.emailSender.EmailController;
import com.mateuszskocz.server.APBFRANCE.repository.CarRepository;
import com.mateuszskocz.server.APBFRANCE.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApbfranceApplicationTests {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ApbComparator apbComparator;

    @Autowired
    private EmailController emailController;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCreateCar() {

        Car car = new Car();
        car.setMarka("VW");
        car.setModel("Polo");
        car.setRok("2002");
        car.setId("1");
        car.setCena("9999");
        car.setEnergy("B");
        car.setKilometerage("12");

        carRepository.save(car);

        Car output = new Car();

        if (!carRepository.findById(car.getId()).isEmpty())
            output = carRepository.findById(car.getId()).get(0);

        assertEquals("VW", output.getMarka());
        assertEquals("Polo", output.getModel());
        assertEquals("2002", output.getRok());
        assertEquals("9999", output.getCena());
        assertEquals("1", output.getId());
        assertEquals("B", output.getEnergy());
        assertEquals("12", output.getKilometerage());

//        System.out.println(output);
    }

    @Test
    public void checkIfExist() {
        Car car = new Car();
        car.setMarka("VW");
        car.setModel("Polo");
        car.setRok("2002");
        car.setId("1");
        car.setCena("9999");
        car.setEnergy("B");
        car.setKilometerage("12");
        car.setCategory("Dobry");

        carRepository.save(car);

        boolean result = apbComparator.checkInDB(car.getId());
        assertTrue(result);
    }

    @Test
    public void checkIfNotExist() {
        Car car = new Car();
        car.setMarka("VW");
        car.setModel("Polo");
        car.setRok("2002");
        car.setId("1");
        car.setCena("9999");
        car.setEnergy("B");
        car.setKilometerage("12");

        carRepository.save(car);

        boolean result = apbComparator.checkInDB("bla");
        assertFalse(result);

//        System.out.println("Nowe auta: " + apbComparator.checkAPBNewCars());

    }


    @Test
    public void sendEmail(){
        List<Car> carList = apbComparator.checkAPBNewCars();

        for (Car car: carList) {
            emailController.sendCar(car);

        }
    }


}
