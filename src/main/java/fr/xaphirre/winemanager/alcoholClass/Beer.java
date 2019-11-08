package fr.xaphirre.winemanager.alcoholClass;

import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;

import java.util.Date;

public class Beer extends Alcohol {
    private TypeBeer type;
    //Instancier

    public Beer(String name, String region, int age, int degreeOfAlcohol, int capacityML, TypeBeer type) {
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
        this.type = type;
        this.date = null;
    }
    public Beer(String name, String region, int age, int degreeOfAlcohol, int capacityML, TypeBeer type, Date date, int id) {
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
        this.type = type;
        this.date = date;
        this.id = id;
    }
    //Fonction
    public String toString() {
        return "\n"+
                "*---------------------------------------------*\n" +
                "Bière : "+ this.name+
                "\n \t Elle provient de "+ this.region+" et à été crée en "+ this.age+". " +
                "\n \t C'est une bière "+ this.type.getName()+"."+
                "\n \t Elle possède "+ this.degreeOfAlcohol+"% d'Alcool et contient "+ this.capacityML +"ml.\n"+
                "*---------------------------------------------*";
    }
    //Getter

    public TypeBeer getType() {
        return this.type;
    }
    //Setter

    public void setType(TypeBeer type) {
        this.type = type;
    }
}
