import alcoholClass.Beer;
import alcoholClass.typeAlcohol.TypeBeer;

class BeerTest {
    @org.junit.jupiter.api.Test
    void getType() {
        Beer beer = new Beer("Heineken", "Lausanne", 2010, 6, 330, TypeBeer.BLONDE);
        assertEquals(TypeBeer.BLONDE, beer.getType());
    }

    @org.junit.jupiter.api.Test
    void setType() {
        Beer beer = new Beer("Heineken", "Lausanne", 2010, 6, 330, TypeBeer.BLONDE);
        beer.setType(TypeBeer.BLANCHE);
        assertEquals(TypeBeer.BLANCHE, beer.getType());
    }
}