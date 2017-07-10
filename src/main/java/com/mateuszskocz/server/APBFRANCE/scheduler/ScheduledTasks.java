package com.mateuszskocz.server.APBFRANCE.scheduler;

/**
 * Created by Mati on 08.07.2017.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mateuszskocz.server.APBFRANCE.pageReader.ApbFrancePageReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final ApbFrancePageReader apbFrancePageReader;


    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ScheduledTasks(ApbFrancePageReader apbFrancePageReader) {
        this.apbFrancePageReader = apbFrancePageReader;
    }

//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }


    @Scheduled(fixedRate = 5000)
    public void checkCarsUsed() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        apbFrancePageReader
                .getCarsUsed()
                .stream()
                .forEach(System.out::println);

        System.out.println("\n\n\n\n\n");
    }
}