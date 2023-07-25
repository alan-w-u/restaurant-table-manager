package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    Menu menu;

    @BeforeEach
    void runBefore() {
        menu = new Menu();
    }

    @Test
    void testGetNumberOfItemsOnMenu() {
        assertEquals(4, menu.getNumberOfItemsOnMenu());
    }

    @Test
    void testGetSpecificItem() {
        assertEquals(menu.m1, menu.getSpecificItem(1));
        assertEquals(menu.m2, menu.getSpecificItem(2));
        assertEquals(menu.m3, menu.getSpecificItem(3));
        assertEquals(menu.m4, menu.getSpecificItem(4));
    }

    @Test
    void testGetNameOfSpecificItem() {
        assertEquals("Fried Rice", menu.getNameOfSpecificItem(1));
        assertEquals("Chow Mein", menu.getNameOfSpecificItem(2));
        assertEquals("Peking Duck", menu.getNameOfSpecificItem(3));
        assertEquals("Sweet and Sour Pork", menu.getNameOfSpecificItem(4));
    }

    @Test
    void testGetPriceOfSpecificItem() {
        assertEquals(12.99, menu.getPriceOfSpecificItem(1));
        assertEquals(13.99, menu.getPriceOfSpecificItem(2));
        assertEquals(15.99, menu.getPriceOfSpecificItem(3));
        assertEquals(9.99, menu.getPriceOfSpecificItem(4));
    }

    @Test
    void testGetItemsOnMenu() {
        assertEquals("1. Fried Rice $12.99\n2. Chow Mein $13.99\n3. Peking Duck $15.99\n" +
                        "4. Sweet and Sour Pork $9.99", menu.getItemsOnMenu());
    }
    @Test
    void testGetMenuItemsByString() {
        assertEquals(menu.m1, menu.getMenuItemsByString("Fried Rice"));
        assertEquals(menu.m2, menu.getMenuItemsByString("Chow Mein"));
        assertNull(menu.getMenuItemsByString("Raw Chicken"));
    }
}
