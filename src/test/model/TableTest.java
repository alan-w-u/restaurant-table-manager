package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    Table t1;
    Table t2;
    Table t3;
    Table t4;
    Table t5;

    MenuItem m1;
    MenuItem m2;

    @BeforeEach
    void runBefore() {
        t1 = new Table();

        t2 = new Table();
        t2.changeAvailability();

        t3 = new Table();
        t3.changeAvailability();
        t3.changeAvailability();

        t4 = new Table();
        t4.changeAvailability();
        t4.changeAvailability();
        t4.changeAvailability();

        m1 = new MenuItem("Fried Rice", 12.99);
        m2 = new MenuItem("Chow Mein", 13.99);

        t5 = new Table();
        t5.changeAvailability();
        t5.addMenuItem(m1);
        t5.addMenuItem(m2);
    }

    @Test
    void testConstructor() {
        assertTrue(t1.getTableOrder().isEmpty());
        assertEquals("available", t1.getAvailability());

        assertTrue(t2.getTableOrder().isEmpty());
        assertEquals("occupied", t2.getAvailability());

        assertTrue(t3.getTableOrder().isEmpty());
        assertEquals("ready to pay", t3.getAvailability());

        assertTrue(t4.getTableOrder().isEmpty());
        assertEquals("needs cleaning", t4.getAvailability());
    }

    @Test
    void testChangeAvailability() {
        assertEquals("available", t1.getAvailability());
        t1.changeAvailability();
        assertEquals("occupied", t1.getAvailability());

        assertEquals("occupied", t2.getAvailability());
        t2.changeAvailability();
        assertEquals("ready to pay", t2.getAvailability());

        assertEquals("ready to pay", t3.getAvailability());
        t3.changeAvailability();
        assertEquals("needs cleaning", t3.getAvailability());

        assertEquals("needs cleaning", t4.getAvailability());
        t4.changeAvailability();
        assertEquals("available", t4.getAvailability());
    }

    @Test
    void testAddMenuItem() {
        assertFalse(t1.addMenuItem(m1));
        assertTrue(t2.addMenuItem(m1));
        assertFalse(t3.addMenuItem(m1));
        assertFalse(t4.addMenuItem(m1));
    }

    @Test
    void testGetNameOfSpecificItem() {
        assertEquals("Fried Rice", t5.getNameOfSpecificItem(1));
        assertEquals("Chow Mein", t5.getNameOfSpecificItem(2));
    }

    @Test
    void testGetPriceOfSpecificItem() {
        assertEquals(12.99, t5.getPriceOfSpecificItem(1));
        assertEquals(13.99, t5.getPriceOfSpecificItem(2));
    }

    @Test
    void testGetNumberOfItemsOrdered() {
        assertEquals(2, t5.getNumberOfItemsOrdered());
    }

    @Test
    void testGetNameAllItemsOrdered() {
        List<String> list = new ArrayList<String>();
        list.add("Fried Rice");
        list.add("Chow Mein");

        assertEquals(list, t5.getNameAllItemsOrdered());
    }

    @Test
    void testGetPriceAllItemsOrdered() {
        List<Double> list = new ArrayList<Double>();
        list.add(12.99);
        list.add(13.99);

        assertEquals(list, t5.getPriceAllItemsOrdered());
    }

    @Test
    void testGetTotalPriceAllItemsOrdered() {
        assertEquals(26.98, t5.getTotalPriceAllItemsOrdered());
    }
}