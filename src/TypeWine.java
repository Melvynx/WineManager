public enum TypeWine {
    Blanc ("Blanc"),
    Rouge ("Rouge"),
    Rose ("Rosé");

    private String name = new String();
    TypeWine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
