package alcoholClass;

import alcoholClass.Alcohol;

public class StrongAlcohol extends Alcohol {
    //Instancier

    public StrongAlcohol() { }
    public StrongAlcohol(String name, String region, int age, int degreeOfAlcohol, int capacityML) {
        this.name = name;
        this.region = region;
        this.age = age;
        this.degreeOfAlcohol = degreeOfAlcohol;
        this.capacityML = capacityML;
    }
}
