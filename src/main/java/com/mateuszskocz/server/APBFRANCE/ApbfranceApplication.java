package com.mateuszskocz.server.APBFRANCE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@ComponentScan
//@EnableScheduling
//@EnableEmailTools
public class ApbfranceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApbfranceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\nDziala jak zloto\n");
    }
}
