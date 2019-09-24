public enum TypeBeer{
    Blanche ("Blanche"),
    Blonde ("Blonde"),
    Brune ("Brune"),
    Rousse ("Rousse");

    private String name;
    TypeBeer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
