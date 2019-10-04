import com.sun.istack.internal.Nullable;

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

    @Nullable
    static public TypeBeer getFromName(String name) {
        for (TypeBeer candidate : values()) {
            if (candidate.name.equals(name)) {
                return candidate;
            }
        }
        return null;
    }
}
