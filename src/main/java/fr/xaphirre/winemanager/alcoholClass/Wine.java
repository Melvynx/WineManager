package fr.xaphirre.winemanager.alcoholClass;

import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;

import java.util.Date;

public class Wine extends Alcohol {
    private TypeWine type;
    private int startMaturity;
    private int endMaturity;
    //Instancier

    public Wine(String name, String region, int age, int degreeOfAlcohol, int capacityML, TypeWine type, int startMaturity, int endMaturity) {
        super();
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
        this.type = type;
        this.startMaturity = startMaturity;
        this.endMaturity = endMaturity;
        this.date = null;
    }
    public Wine(String name, String region, int age, int degreeOfAlcohol, int capacityML, TypeWine type, int startMaturity, int endMaturity, Date date, int id) {
        super();
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
        this.type = type;
        this.startMaturity = startMaturity;
        this.endMaturity = endMaturity;
        this.date = date;
        this.id = id;
    }
    //Fonction
    public String toString() {
        return "\n" +
                "*---------------------------------------------*\n" +
                "Vin : " + this.name +
                "\n \t Il provient de " + this.region + " et à été crée en " + this.age + ". " +
                "\n \t C'est un vin " + this.type.getName() + ". Il est à consommer de préfèrence entre " + this.startMaturity + " et " + this.endMaturity +
                "\n \t Il possède " + this.degreeOfAlcohol + "% d'Alcool et contient " + this.capacityML + "ml.\n" +
                "*---------------------------------------------*";
    }
    //Getter

    public TypeWine getType() {
        return this.type;
    }

    public String getMaturity() {
        return startMaturity + " - " + endMaturity;
    }

    public int getStartMaturity() {
        return startMaturity;
    }

    public int getEndMaturity() {
        return endMaturity;
    }
//Setter

    public void setType(TypeWine type) {
        this.type = type;
    }
}
