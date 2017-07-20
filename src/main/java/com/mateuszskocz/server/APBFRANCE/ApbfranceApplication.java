package com.mateuszskocz.server.APBFRANCE;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableScheduling
public class ApbfranceApplication extends SpringBootServletInitializer implements CommandLineRunner  {

    public static void main(String[] args) {
        SpringApplication.run(ApbfranceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
