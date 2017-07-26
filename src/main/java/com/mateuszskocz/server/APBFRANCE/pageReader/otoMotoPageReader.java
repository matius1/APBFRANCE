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
public class otoMotoPageReader {

    public static List<Car> getCarsUsed() {
        return getCars();
    }


    public static List<Car> getCars() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://auto-piece.otomoto.pl/").get();
        } catch (IOException e) {
            System.err.println("Nie udalo sie pobrac samochodow ze strony");
            e.printStackTrace();
        }

        Elements tables = doc.getElementsByClass("offer-item__content");

        ArrayList<Car> cars = new ArrayList<Car>();

        for (Element table : tables) {
            Car car;
            try {
                car = new Car(
                        table.getElementsByClass("observe-link").attr("data-id"),
                        table.getElementsByClass("offer-title offer-title--short").first().text(),
                        table.getElementsByClass("offer-item__params-item").get(1).text(),
                        table.getElementsByClass("offer-price__number").first().text(),
                        " ",
                        table.getElementsByClass("offer-item__params-item").get(2).text()
                );
                car.setCategory("Uzywany - OtoMoto");
                car.setLink(table.getElementsByClass("offer-title__link").attr("href"));
            } catch (Exception e) {
                System.err.println("Blad przy parsowaniu samochodu - otomoto: ");
//                    e.printStackTrace();
                car = new Car();
                car.setId("1");
            }
//                System.out.println(car);
            cars.add(car);
        }
        return cars;
    }

}
