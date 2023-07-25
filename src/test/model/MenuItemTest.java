package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {

    Menu menu;

    @BeforeEach
    void runBefore() {
        menu = new Menu();
    }

    @Test
    void testConstructor() {
        assertEquals("Fried Rice", menu.m1.getMenuItemName());
        assertEquals(12.99, menu.m1.getMenuItemPrice());

        assertEquals("Chow Mein", menu.m2.getMenuItemName());
        assertEquals(13.99, menu.m2.getMenuItemPrice());
    }

    @Test
    void testSetMenuItemName() {
        assertEquals("Fried Rice", menu.m1.getMenuItemName());
        menu.m1.setMenuItemName("White Rice");
        assertEquals("White Rice", menu.m1.getMenuItemName());
    }

    @Test
    void testSetMenuItemPrice() {
        assertEquals(12.99, menu.m1.getMenuItemPrice());
        menu.m1.setMenuItemPrice(5.99);
        assertEquals(5.99, menu.m1.getMenuItemPrice());
    }
}
