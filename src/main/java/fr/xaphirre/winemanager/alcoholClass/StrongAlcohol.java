package fr.xaphirre.winemanager.alcoholClass;

import java.util.Date;

public class StrongAlcohol extends Alcohol {
    //Instancier

    public StrongAlcohol(String name, String region, int age, int degreeOfAlcohol, int capacityML, Date date, int id) {
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
        this.date = date;
        this.id = id;
    }
    public StrongAlcohol(String name, String region, int age, int degreeOfAlcohol, int capacityML) {
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
    }
}
