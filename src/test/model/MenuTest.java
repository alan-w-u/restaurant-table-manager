package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    Menu menu;

    MenuItem m1;
    MenuItem m2;
    MenuItem m3;
    MenuItem m4;

    @BeforeEach
    void runBefore() {
        menu = new Menu();

//        m1 = new MenuItem("Fried Rice", 12.99);
//        m2 = new MenuItem("Chow Mein", 13.99);
//        m3 = new MenuItem("Peking Duck", 15.99);
//        m4 = new MenuItem("Sweet and Sour Pork", 9.99);
//
//        testMenu.addItemToMenu(m1);
//        testMenu.addItemToMenu(m2);
//        testMenu.addItemToMenu(m3);
//        testMenu.addItemToMenu(m4);
    }

    @Test
    void testGetNumberOfItemsOnMenu() {
        assertEquals(4, menu.getNumberOfItemsOnMenu());
    }

    @Test
    void testGetSpecificItem() {
        assertEquals(menu.getSpecificItem(1), menu.getSpecificItem(1));
        assertEquals(menu.getSpecificItem(2), menu.getSpecificItem(2));
        assertEquals(menu.getSpecificItem(3), menu.getSpecificItem(3));
        assertEquals(menu.getSpecificItem(4), menu.getSpecificItem(4));
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
}
