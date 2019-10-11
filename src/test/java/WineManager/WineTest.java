import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WineTest {

    @Test
    void getMaturity() {
        Wine wine = new Wine("Heineken", "Lausanne", 2010, 6, 330, TypeWine.ROSE, 2000, 2025);
        assertEquals("2000 - 2025", wine.getMaturity());
    }

    @Test
    void setType() {
        Wine wine = new Wine("Heineken", "Lausanne", 2010, 6, 330, TypeWine.ROSE, 2000, 2025);

        wine.setType(TypeWine.ROUGE);
        assertEquals(TypeWine.ROUGE, wine.getType());
    }
}