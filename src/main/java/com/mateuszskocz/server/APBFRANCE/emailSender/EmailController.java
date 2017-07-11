package com.mateuszskocz.server.APBFRANCE.emailSender;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    public String sendHealth(List<String> recipientEmail) {
        Context context = new Context();
        context.setVariable("header", "System działa - " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + " " + LocalDate.now());
        String body = templateEngine.process("health", context);

        for (String email : recipientEmail)
            emailSender.sendEmail(email, "Informacja o systemie", body);

        return "index";
    }


    public String sendCar(Car car, List<String> recipientEmail) {
        Context context = new Context();
        context.setVariable("header", "Nowe auto na APBFRANCE - " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + " " + LocalDate.now());
        context.setVariable("model", car.getModel());
        context.setVariable("rok", car.getRok());
        context.setVariable("cena", car.getCena());
        context.setVariable("paliwo", car.getEnergy());
        context.setVariable("przebieg", car.getKilometerage());
        context.setVariable("stan", car.getCategory());
        context.setVariable("title", car.getModel() + ", " + car.getEnergy() + ", " + car.getRok() + ", " + car.getCena());

        String category = car.getCategory().equals("Dobry Stan") ? "used" : "damaged";

        context.setVariable("url", "http://www.apbfrance.com/catalog/" + category + "/" + car.getId());

        String body = templateEngine.process("new_car_apb_template", context);
        for (String email : recipientEmail)
            emailSender.sendEmail(email, "Nowe auto na APBFRANCE - ", body);

        return "index";
    }
}