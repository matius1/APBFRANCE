package com.mateuszskocz.server.APBFRANCE;

import com.mateuszskocz.server.APBFRANCE.comparator.ApbComparator;
import com.mateuszskocz.server.APBFRANCE.comparator.ComparatorInterface;
import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.domain.Recipient;
import com.mateuszskocz.server.APBFRANCE.emailSender.EmailController;
import com.mateuszskocz.server.APBFRANCE.repository.CarRepository;
import com.mateuszskocz.server.APBFRANCE.repository.RecipientRepository;
import com.mateuszskocz.server.APBFRANCE.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

    @Autowired
    private RecipientRepository recipientRepository;


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


//    @Test
//    public void sendEmail() {
//
//        List<String> emails = new ArrayList<>();
//        emails.add("matius1matius@gmail.com");
////        emails.add("wasekbasia@wp.pl");
//
//        List<Car> carList = apbComparator.checkAPBNewCars();
//
//        for (Car car : carList)
//            emailController.sendCar(car, emails);
//
//    }

//    @Test
//    public void sendEmailHealth() {
//
//        List<String> emails = new ArrayList<>();
//        emails.add("matius1matius@gmail.com");
////        emails.add("wasekbasia@wp.pl");
//
//        emailController.sendHealth(emails);
//
//    }

//
    @Test
    public void addEmailAndSend() {
//        Recipient recipient = new Recipient();
//        recipient.setEmail("matius1matius@gmail.com");
//        recipientRepository.save(recipient);
//        recipient.setEmail("wasekbasia@wp.pl");
//        recipientRepository.save(recipient);

        List<Recipient> recipientList = recipientRepository.findAll();
        List<String> emailList = new ArrayList<>();
        for (Recipient rec : recipientList) {
            emailList.add(rec.getEmail());
        }

        emailController.sendHealth(emailList);
    }


//    @Test
//    public void readOtoMoto(){
//        System.err.println("Wysylam informacje o nowych samochodach");
//
//        List<Recipient> recipientList = recipientRepository.findAll();
//        List<String> emailList = new ArrayList<>();
//        for (Recipient rec : recipientList) {
//            emailList.add(rec.getEmail());
//        }
//
//        List<Car> carList = apbComparator.checkAPBNewCars();
//
//        try {
//            for (Car car : carList){
//                System.out.println(car);
//                emailController.sendCar(car, emailList);
//            }
//        } catch (Exception e) {
//            System.err.println("Wystapil blad podczas sprawdzania aut - ReadOtoMoto from SendCar" );
//            e.printStackTrace();
//            System.err.println("\n\n" );
//
//        }
//    }


}
