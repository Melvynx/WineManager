import com.sun.istack.internal.Nullable;

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
    @Nullable
    static public TypeWine getFromName(String name) {
        for (TypeWine candidate : values()) {
            if (candidate.name.equals(name)) {
                System.out.println(candidate);
                return candidate;
            }
        }
        return null;
    }
}