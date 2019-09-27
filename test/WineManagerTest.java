import com.sun.corba.se.pept.encoding.InputObject;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
/*
class WineManagerTest {

    @Test
    void TestExit() {
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WineManager.main(null);
    }

    @Test
    void TestAddWine() {

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

        assertEquals(0, WineManager.alcohols.size());
        WineManager.main(null);
        assertEquals(7, WineManager.alcohols.size());
    }
    @Test
    void TestAddBeer() {



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

        assertEquals(0, WineManager.alcohols.size());
        WineManager.main(null);
        assertEquals(7, WineManager.alcohols.size());
    }
    @Test
    void TestAddAlcohol() {

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

        assertEquals(0, WineManager.alcohols.size());
        WineManager.main(null);
        assertEquals(7, WineManager.alcohols.size());
    }
    @Test
    void TestShowAll() {

        String input = "2\n" +
                "1\n" +
                "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        WineManager.main(null);
        assertEquals(6, WineManager.alcohols.size());
    }
    @Test
    void TestShowBeer() {

        String input = "2\n" +
                "3\n" +
                "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        WineManager.main(null);
        assertEquals(6, WineManager.alcohols.size());
    }
}

 */