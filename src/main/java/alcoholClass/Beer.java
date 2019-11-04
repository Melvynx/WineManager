package alcoholClass;

import alcoholClass.typeAlcohol.TypeBeer;

public class Beer extends Alcohol {
    protected TypeBeer type;
    //Instancier

    public Beer(String name, String region, int age, int degreeOfAlcohol, int capacityML, TypeBeer type) {
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
        this.type = type;
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
