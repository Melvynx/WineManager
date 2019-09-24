public class Wine extends Alcohol {
    protected String type;
    protected int startMaturity;
    protected int endMaturity;
    //Instancier

    public Wine() { }

    public Wine(String name, String region, int age, int degreeOfAlcohol, int capacityML, String type, int startMaturity, int endMaturity) {
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
        String str = "\n"+
                "*---------------------------------------------*\n" +
                "Vin : "+this.name+
                "\n \t Il provient de "+this.region+" et à été crée en "+this.age+". " +
                "\n \t C'est un vin "+this.type+". Il est à consommer de préfèrence entre "+this.startMaturity+" et "+this.endMaturity+
                "\n \t Il possède "+this.degreeOfAlcohol+"% d'Alcool et contient "+ this.capacityML +"ml.\n"+
                "*---------------------------------------------*";
        return str;
    }
    //Getter

    public String getType() {
        return type;
    }
    public String getMaturity() {
        String str = startMaturity + " - " + endMaturity;
        return str;
    }
    //Setter

    public void setType(String type) {
        this.type = type;
    }
    public void setEndMaturity(int endMaturity) {
        this.endMaturity = endMaturity;
    }
    public void setStartMaturity(int startMaturity) {
        this.startMaturity = startMaturity;
    }
}
