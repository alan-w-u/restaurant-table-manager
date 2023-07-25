package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    Menu menu;

    Restaurant r1;
    Restaurant r2;

    @BeforeEach
    void runBefore() {
        menu = new Menu();

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
        r2.getSpecificTable(2).changeAvailability();
        r2.getSpecificTable(3).changeAvailability();

        r2.getSpecificTable(2).addMenuItem(menu.m1);
        r2.getSpecificTable(3).addMenuItem(menu.m2);

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
    void testAddSpecificTable() {
        Table t = new Table();
        t.changeAvailabilityTo(2);
        r1.addSpecificTable(t);
        assertEquals(1, r1.getNumberOfTables());
        assertEquals("ready to pay", r1.getSpecificTable(1).getAvailability());
    }

    @Test
    void testGetAllAvailability() {
        r2.getSpecificTable(2).changeAvailability();
        r2.getSpecificTable(3).changeAvailabilityTo(2);
        r2.getSpecificTable(4).changeAvailabilityTo(3);
        assertEquals("Table 1: available\nTable 2: occupied\nTable 3: ready to pay\nTable 4: needs cleaning",
                r2.getAllAvailability());
    }

    @Test
    void testGetAllOrders() {
        r1.addTable();
        r1.getSpecificTable(1).changeAvailabilityTo(1);
        r1.getSpecificTable(1).addMenuItem(menu.m1);
        assertEquals("Table 1:\nFried Rice x1", r1.getAllOrders());

        r2.getSpecificTable(2).changeAvailabilityTo(1);
        r2.getSpecificTable(2).addMenuItem(menu.m1);
        r2.getSpecificTable(2).addMenuItem(menu.m2);
        assertEquals("Table 1: no orders\n\nTable 2:\nFried Rice x1\nChow Mein x1\n\nTable 3: no orders\n\n"
                + "Table 4: no orders", r2.getAllOrders());
    }
}
