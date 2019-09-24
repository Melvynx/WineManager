public enum TypeBeer{
    BLANCHE ("Blanche"),
    BLONDE ("Blonde"),
    BRUNE ("Brune"),
    ROUSSE ("Rousse");

    private String name;
    TypeBeer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
