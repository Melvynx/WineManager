public enum TypeWine {
    BLANC ("Blanc"),
    ROUGE ("Rouge"),
    ROSE ("Rosé");

    private String name = new String();
    TypeWine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
