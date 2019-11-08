package fr.xaphirre.winemanager.alcoholClass;

import java.util.Date;

public abstract class Alcohol {
    //Variable
    protected String name;
    protected String region;

    protected int age;
    protected int degreeOfAlcohol;
    protected int capacityML;
    Date date;

    protected int id;
    //Fonction
    public String toString() {
        return "\n"+
                "*---------------------------------------------*\n" +
                "Alcool : "+ this.name+
                "\n \t Il provient de "+ this.region+" et à été crée en "+ this.age+". " +
                "\n \t Il possède "+ this.degreeOfAlcohol+"% d'Alcool."+
                "\n \t La bouteille contient "+ this.capacityML+"ml.\n"+
                "*---------------------------------------------*";
    }
    //Getter
    public String getName() {
        return name;
    }
    public String getRegion() {
        return region;
    }
    public int getAge() {
        return age;
    }
    public int getDegreeOfAlcohol() {
        return degreeOfAlcohol;
    }
    public int getCapacityML() {
        return capacityML;
    }
    public int getId() {
        return id;
    }
    //Setter
    public void setAge(int age) {
        this.age = age;
    }
    public void setCapacityML(int capacityML) {
        this.capacityML = capacityML;
    }
    public void setDegreeOfAlcohol(int degreeOfAlcohol) {
        this.degreeOfAlcohol = degreeOfAlcohol;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public Date getDate() {
        return date;
    }
}
