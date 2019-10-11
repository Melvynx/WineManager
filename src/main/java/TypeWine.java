

public enum TypeWine {
    BLANC ("Blanc"),
    ROUGE ("Rouge"),
    ROSE ("Rosé");

    private String name;
    TypeWine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static public TypeWine getFromName(String name) {
        for (TypeWine candidate : values()) {
            if (candidate.name.equals(name)){
                return candidate;
            }
        }
        return null;
    }
}