package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    Menu menu;

    Table t1;
    Table t2;
    Table t3;
    Table t4;
    Table t5;

    @BeforeEach
    void runBefore() {
        menu = new Menu();

        t1 = new Table(1);

        t2 = new Table(2);
        t2.changeAvailability();

        t3 = new Table(3);
        t3.changeAvailability();
        t3.changeAvailability();

        t4 = new Table(4);
        t4.changeAvailability();
        t4.changeAvailability();
        t4.changeAvailability();

        t5 = new Table(5);
        t5.changeAvailability();
        t5.addMenuItem(menu.m1);
        t5.addMenuItem(menu.m2);
    }

    @Test
    void testConstructor() {
        assertTrue(t1.getTableOrder().isEmpty());
        assertEquals(1, t1.getTableNumber());
        assertEquals("available", t1.getAvailability());

        assertTrue(t2.getTableOrder().isEmpty());
        assertEquals(2, t2.getTableNumber());
        assertEquals("occupied", t2.getAvailability());

        assertTrue(t3.getTableOrder().isEmpty());
        assertEquals(3, t3.getTableNumber());
        assertEquals("ready to pay", t3.getAvailability());

        assertTrue(t4.getTableOrder().isEmpty());
        assertEquals(4, t4.getTableNumber());
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
    void testChangeAvailabilityTo() {
        assertEquals("available", t1.getAvailability());
        t1.changeAvailabilityTo(2);
        assertEquals("ready to pay", t1.getAvailability());

        assertEquals("occupied", t2.getAvailability());
        t2.changeAvailabilityTo(0);
        assertEquals("available", t2.getAvailability());
    }

    @Test
    void testAddMenuItem() {
        assertFalse(t1.addMenuItem(menu.m1));
        assertTrue(t2.addMenuItem(menu.m1));
        assertFalse(t3.addMenuItem(menu.m1));
        assertFalse(t4.addMenuItem(menu.m1));
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
    void testGetUniqueItems() {
        assertEquals(0, t1.getUniqueItems());
        assertEquals(2, t5.getUniqueItems());
    }

    @Test
    void testGetAllItemsOrdered() {
        t5.addMenuItem(menu.m2);
        t5.addMenuItem(menu.m1);
        assertEquals("Fried Rice x2\nChow Mein x2", t5.getAllItemsOrdered());
    }

    @Test
    void testGetTotalPriceAllItemsOrdered() {
        assertEquals(26.98, t5.getTotalPriceAllItemsOrdered());
    }

    @Test
    void testResetOrder() {
        assertEquals(2, t5.getNumberOfItemsOrdered());
        t5.resetOrder();
        assertEquals(0, t5.getNumberOfItemsOrdered());
    }
}