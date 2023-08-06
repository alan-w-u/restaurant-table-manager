package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    Menu menu;

    @BeforeEach
    void runBefore() {
        menu = new Menu();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Restaurant r = new Restaurant(0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRestaurant() {
        try {
            Restaurant r = new Restaurant(0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRestaurant.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRestaurant.json");
            r = reader.read();
            assertEquals(0, r.getNumberOfTables());
            assertEquals("", r.getAllOrders());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRestaurant() {
        try {
            Restaurant r = new Restaurant(0);
            Table t1 = new Table(1);
            Table t2 = new Table(2);
            t2.changeAvailabilityTo(1);
            t2.addMenuItem(menu.m1);
            t2.addMenuItem(menu.m1);
            r.addSpecificTable(t1);
            r.addSpecificTable(t2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRestaurant.json");
            writer.open();
            writer.write(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRestaurant.json");
            r = reader.read();

            assertEquals(2, r.getNumberOfTables());
            assertEquals("available", r.getSpecificTable(1).getAvailability());
            assertEquals("occupied", r.getSpecificTable(2).getAvailability());
            checkTable(r.getSpecificTable(1), "available", r.getSpecificTable(1).getTableOrder());
            checkTable(r.getSpecificTable(2), "occupied", r.getSpecificTable(2).getTableOrder());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
