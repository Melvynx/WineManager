package fr.xaphirre.winemanager.alcoholClass;

import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;

public class Wine extends Alcohol {
    protected TypeWine type;
    protected int startMaturity;
    protected int endMaturity;
    //Instancier

    public Wine() {
    }

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
    }

    //Fonction
    public String toString() {
        String str = "\n" +
                "*---------------------------------------------*\n" +
                "Vin : " + this.name +
                "\n \t Il provient de " + this.region + " et à été crée en " + this.age + ". " +
                "\n \t C'est un vin " + this.type.getName() + ". Il est à consommer de préfèrence entre " + this.startMaturity + " et " + this.endMaturity +
                "\n \t Il possède " + this.degreeOfAlcohol + "% d'Alcool et contient " + this.capacityML + "ml.\n" +
                "*---------------------------------------------*";
        return str;
    }
    //Getter

    public TypeWine getType() {
        return this.type;
    }

    public String getMaturity() {
        String str = startMaturity + " - " + endMaturity;
        return str;
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

    public void setEndMaturity(int endMaturity) {
        this.endMaturity = endMaturity;
    }

    public void setStartMaturity(int startMaturity) {
        this.startMaturity = startMaturity;
    }
}
