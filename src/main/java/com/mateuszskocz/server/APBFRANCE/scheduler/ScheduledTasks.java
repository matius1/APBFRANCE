package com.mateuszskocz.server.APBFRANCE.scheduler;

/**
 * Created by Mati on 08.07.2017.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mateuszskocz.server.APBFRANCE.comparator.ApbInterface;
import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.domain.Recipient;
import com.mateuszskocz.server.APBFRANCE.emailSender.EmailController;
import com.mateuszskocz.server.APBFRANCE.pageReader.ApbFrancePageReader;
import com.mateuszskocz.server.APBFRANCE.repository.RecipientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private ApbFrancePageReader apbFrancePageReader;

    @Autowired
    private ApbInterface apbComparator;

    @Autowired
    private EmailController emailController;

    @Autowired
    private RecipientRepository recipientRepository;


    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 120000)
    public void checkCarsAPBAndSendEmail() {
        System.err.println("Wysylam informacje o nowych samochodach");

        List<Recipient> recipientList = recipientRepository.findAll();
        List<String> emailList = new ArrayList<>();
        for (Recipient rec : recipientList) {
            emailList.add(rec.getEmail());
        }

        List<Car> carList = apbComparator.checkAPBNewCars();

        try {
            for (Car car : carList)
                emailController.sendCar(car, emailList);
        } catch (Exception e) {
            System.err.println("Wystapil blad podczas sprawdzania aut - Scheduler");
            e.printStackTrace();
        }

    }

    @Scheduled(cron = "0 00 18 * * ?")
    public void sendHealthInfo() {
        System.err.println("Wysylam informacje o systemie");

        List<Recipient> recipientList = recipientRepository.findAll();
        List<String> emailList = new ArrayList<>();
        for (Recipient rec : recipientList) {
            emailList.add(rec.getEmail());
        }

        try {
            emailController.sendHealth(emailList);
        } catch (Exception e) {
            System.err.println("Wystapil blad podczas wysylania statusu o aplikacji \n" + e.getStackTrace());
            e.printStackTrace();
        }
    }


}