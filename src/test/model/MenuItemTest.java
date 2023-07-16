package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {

    MenuItem m1;
    MenuItem m2;

    @BeforeEach
    void runBefore() {
        m1 = new MenuItem("Fried Rice", 12.99);
        m2 = new MenuItem("Chow Mein", 13.99);
    }

    @Test
    void testConstructor() {
        assertEquals("Fried Rice", m1.getMenuItemName());
        assertEquals(12.99, m1.getMenuItemPrice());

        assertEquals("Chow Mein", m2.getMenuItemName());
        assertEquals(13.99, m2.getMenuItemPrice());
    }
}
