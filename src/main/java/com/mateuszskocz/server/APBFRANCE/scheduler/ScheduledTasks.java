package com.mateuszskocz.server.APBFRANCE.scheduler;

/**
 * Created by Mati on 08.07.2017.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mateuszskocz.server.APBFRANCE.comparator.ApbInterface;
import com.mateuszskocz.server.APBFRANCE.domain.Car;
import com.mateuszskocz.server.APBFRANCE.emailSender.EmailController;
import com.mateuszskocz.server.APBFRANCE.pageReader.ApbFrancePageReader;
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

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 120000)
    public void checkCarsAPBAndSendEmail() {
        System.err.println("Wysylam informacje o nowych samochodach");

        List<String> emails = new ArrayList<>();
        emails.add("matius1matius@gmail.com");
        emails.add("wasekbasia@wp.pl");


        List<Car> carList = apbComparator.checkAPBNewCars();

        try {
            for (Car car : carList)
                emailController.sendCar(car, emails);
        } catch (Exception e) {
            System.err.println("Wystapil blad podczas sprawdzania aut \n" + e.getStackTrace());
            e.printStackTrace();
        }

    }

    @Scheduled(cron = "0 30 17 * * ?")
    public void sendHealthInfo() {
        System.err.println("Wysylam informacje o systemie");
        List<String> emails = new ArrayList<>();
        emails.add("matius1matius@gmail.com");
        emails.add("wasekbasia@wp.pl");

        try {
            emailController.sendHealth(emails);
        } catch (Exception e) {
            System.err.println("Wystapil blad podczas wysylania statusu o aplikacji \n" + e.getStackTrace());
            e.printStackTrace();
        }
    }


}