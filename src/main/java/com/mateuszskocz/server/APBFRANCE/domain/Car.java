package com.mateuszskocz.server.APBFRANCE.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Mati on 08.07.2017.
 */
@Document
public class Car {

    @Id
    public String id;


    public String marka;

    public String model;

    public String rok;

    public String cena;

    public String energy;
    public String kilometerage;

    public String category;

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", rok='" + rok + '\'' +
                ", cena='" + cena + '\'' +
                ", energy='" + energy + '\'' +
                ", kilometerage='" + kilometerage + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Car(String id, String model, String rok, String cena, String energy, String kilometerage) {
        this.id = id;
       // this.marka = marka;
        this.model = model;
        this.rok = rok;
        this.cena = cena;
        this.energy = energy;
        this.kilometerage = kilometerage;
    }


    public Car() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getKilometerage() {
        return kilometerage;
    }

    public void setKilometerage(String kilometerage) {
        this.kilometerage = kilometerage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        if (marka != null ? !marka.equals(car.marka) : car.marka != null) return false;
        return rok != null ? rok.equals(car.rok) : car.rok == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (marka != null ? marka.hashCode() : 0);
        result = 31 * result + (rok != null ? rok.hashCode() : 0);
        return result;
    }
}
