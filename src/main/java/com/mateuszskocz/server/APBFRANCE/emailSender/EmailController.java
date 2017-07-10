package com.mateuszskocz.server.APBFRANCE.emailSender;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
//http://codecouple.pl/2016/09/30/8-spring-boot-email-szablon-i-wysylanie/

/**
 * Created by Mati on 10.07.2017.
 */
@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/")
    public String send() {
        Context context = new Context();
        context.setVariable("header", "Nowe auto na APBFRANCE");
        context.setVariable("model", "Polo");
        context.setVariable("rok", "1999");
        context.setVariable("cena", "2000 $");
        context.setVariable("paliwo", "Diesel");
        context.setVariable("przebieg", "999 999 km");
        context.setVariable("stan", "Dobry stan");
        context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");

        String body = templateEngine.process("template", context);
        emailSender.sendEmail("matius1matius@gmail.com", "EMAIL TEST", body);
        return "index";
    }

    public String sendCar(Car car) {
        Context context = new Context();
        context.setVariable("header", "Nowe auto na APBFRANCE");
        context.setVariable("model", car.getModel());
        context.setVariable("rok", car.getRok());
        context.setVariable("cena", car.getCena());
        context.setVariable("paliwo", car.getEnergy());
        context.setVariable("przebieg", car.getKilometerage());
        context.setVariable("stan", car.getCategory());
        context.setVariable("title", car.getModel() + ", " + car.getEnergy() + ", " + car.getRok() + ", " + car.getCena());

        String body = templateEngine.process("template", context);
        emailSender.sendEmail("matius1matius@gmail.com", "Nowe auto na APBFRANCE - ", body);
        return "index";
    }
}