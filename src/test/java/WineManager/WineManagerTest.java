import fr.xaphirre.winemanager.WineManager;
import fr.xaphirre.winemanager.alcoholClass.Beer;
import fr.xaphirre.winemanager.alcoholClass.StrongAlcohol;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeBeer;
import fr.xaphirre.winemanager.alcoholClass.Wine;
import fr.xaphirre.winemanager.alcoholClass.typeAlcohol.TypeWine;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class WineManagerTest {

    @BeforeEach
    void setup() {
        WineManager.dbPath = "test.db";
    }

    @AfterEach
    void deleteDb() {
        File db = new File("test.db");
        if (db.exists()) {
            db.delete();
        }
    }



    @Test
    void TestExit() throws SQLException {
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WineManager.main(null);

        WineManager.connection.connect();
        assertEquals(0, WineManager.connection.getAllAlcohol().size());
        WineManager.connection.close();
    }

    @Test
    void TestAddWine() throws SQLException {

        String input = "1\n" +
                "1\n" +
                "aa\n" +
                "NameTest\n"+
                "RégionTest\n"+
                "100\n"+
                "2021\n"+
                "2000\n"+
                "30\n"+
                "750\n"+
                "432\n"+
                "Banane\n"+
                "2\n"+
                "1900\n"+
                "2000\n" +
                "2025\n" +
                "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WineManager.main(null);

        WineManager.connection.connect();
        assertEquals(1, WineManager.connection.getAllAlcohol().size());
        WineManager.connection.close();
    }
    @Test
    void TestAddBeer() throws SQLException {



        String input = "1\n" +
                "2\n" +
                "White Frontier\n" +
                "Nord de la Suisse\n"+
                "2018\n"+
                "8\n"+
                "330\n"+
                "2\n"+
                "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WineManager.main(null);

        WineManager.connection.connect();
        assertEquals(1, WineManager.connection.getAllAlcohol().size());
        WineManager.connection.close();
    }
    @Test
    void TestAddAlcohol() throws SQLException {

        String input = "1\n" +
                "3\n" +
                "Jager\n" +
                "Germanique\n"+
                "2018\n"+
                "35\n"+
                "70\n"+
                "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WineManager.main(null);

        WineManager.connection.connect();
        assertEquals(1, WineManager.connection.getAllAlcohol().size());
        WineManager.connection.close();
    }
    @Test
    void TestShowAll() throws SQLException {
        WineManager.connection.connect();
        WineManager.connection.addBeer(new Beer(
                "TestName", "TestRegion", 2019, 30, 1000, TypeBeer.BLANCHE
        ));
        WineManager.connection.addStrongAlcohol(new StrongAlcohol(
                "TestName", "TestRegion", 2019, 30, 1000
        ));
        WineManager.connection.addWine(new Wine(
                "TestName", "TestRegion", 2019, 30, 1000, TypeWine.ROSE, 2019, 2022
        ));
        WineManager.connection.close();

        String input = "2\n" +
                "1\n" +
                "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        WineManager.main(null);

        WineManager.connection.connect();
        assertEquals(3, WineManager.connection.getAllAlcohol().size());
        WineManager.connection.close();
    }
    @Test
    void TestShowBeer() throws SQLException {
        WineManager.connection.connect();
        WineManager.connection.addBeer(new Beer(
                "TestName", "TestRegion", 2019, 30, 1000, TypeBeer.BLANCHE
        ));
        WineManager.connection.close();

        String input = "2\n" +
                "3\n" +
                "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        WineManager.main(null);

        WineManager.connection.connect();
        assertEquals(1, WineManager.connection.getAllAlcohol().size());
        WineManager.connection.close();
    }
}