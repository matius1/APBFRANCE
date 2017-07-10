package com.mateuszskocz.server.APBFRANCE.pageReader;

import com.mateuszskocz.server.APBFRANCE.domain.Car;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 08.07.2017.
 */
@Controller
public class ApbFrancePageReader {

    public static List<Car> getCarsUsed() {
        return getCars("used");
    }
    public static List<Car> getCarsDamaged() {
        return getCars("damaged");
    }


    public static List<Car> getCars(String type) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.apbfrance.com/catalog/"+ type +"?display=list").get();
        } catch (IOException e) {
            System.err.println("Nie udalo sie pobrac samochodow ze strony");
            e.printStackTrace();
        }

        Elements tables = doc.select("tr");
        ArrayList<Car> cars = new ArrayList<Car>();

        for (Element table : tables) {
            if (table.hasAttr("id")) {
                Car car;
                car = new Car(
                        table.getElementsByClass("reference").text(),
                        table.getElementsByClass("model").text(),
                        table.getElementsByClass("year").text(),
                        table.getElementsByClass("price").text(),
                        table.getElementsByClass("energy").text(),
                        table.getElementsByClass("kilometerage").text()
                );
//                System.out.println(car);

                if(type.toLowerCase().equals("used"))
                    car.setCategory("Dobry Stan");

                if(type.toLowerCase().equals("damaged"))
                    car.setCategory("Uszkodzony");

                cars.add(car);
            }
        }
        return cars;
    }

}
