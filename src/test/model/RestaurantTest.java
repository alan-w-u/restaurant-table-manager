package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    Restaurant r1;
    Restaurant r2;

    MenuItem m1;
    MenuItem m2;

    @BeforeEach
    void runBefore() {
        r1 = new Restaurant(0);
        r2 = new Restaurant(4);
    }

    @Test
    void testConstructor() {
        assertEquals(0, r1.getNumberOfTables());
        assertEquals(4, r2.getNumberOfTables());
    }

    @Test
    void testGetSpecificTable() {
        m1 = new MenuItem("Fried Rice", 12.99);
        m2 = new MenuItem("Chow Mein", 13.99);

        r2.getSpecificTable(2).changeAvailability();
        r2.getSpecificTable(3).changeAvailability();

        r2.getSpecificTable(2).addMenuItem(m1);
        r2.getSpecificTable(3).addMenuItem(m2);

        assertEquals("Fried Rice", r2.getSpecificTable(2).getNameOfSpecificItem(1));
        assertEquals(13.99, r2.getSpecificTable(3).getPriceOfSpecificItem(1));
    }

    @Test
    void testAddTable() {
        r1.addTable();
        r1.addTable();
        assertEquals(2, r1.getNumberOfTables());
    }

    @Test
    void testRemoveTable() {
        r2.removeTable();
        r2.removeTable();
        assertEquals(2, r2.getNumberOfTables());
    }

    @Test
    void testAddAmountOfTables() {
        r1.addAmountOfTables(2);
        assertEquals(2, r1.getNumberOfTables());
    }

    @Test
    void testRemoveAmountOfTables() {
        r2.removeAmountOfTables(2);
        assertEquals(2, r2.getNumberOfTables());
    }

    @Test
    void testGetAllAvailability() {
        r2.getSpecificTable(2).changeAvailability();
        r2.getSpecificTable(3).changeAvailabilityTo(2);
        r2.getSpecificTable(4).changeAvailabilityTo(3);
        assertEquals("Table 1: available\nTable 2: occupied\nTable 3: ready to pay\nTable 4: needs cleaning",
                r2.getAllAvailability());
    }
}
