package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Restaurant r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurant() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurant.json");
        try {
            Restaurant r = reader.read();
            assertEquals(0, r.getNumberOfTables());
            assertEquals("", r.getAllOrders());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRestaurant() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurant.json");
        try {
            Restaurant r = reader.read();
            assertEquals(2, r.getNumberOfTables());

            checkTable(r.getSpecificTable(1), "available", r.getSpecificTable(1).getTableOrder());
            checkTable(r.getSpecificTable(2), "occupied", r.getSpecificTable(2).getTableOrder());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
