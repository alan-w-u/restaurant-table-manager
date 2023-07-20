package model;

import java.util.ArrayList;
import java.util.List;

// Represents the menu at a restaurant with a list of menu items
public class Menu {

    private List<MenuItem> menu;

    public final MenuItem m1 = new MenuItem("Fried Rice", 12.99);
    public final MenuItem m2 = new MenuItem("Chow Mein", 13.99);
    public final MenuItem m3 = new MenuItem("Peking Duck", 15.99);
    public final MenuItem m4 = new MenuItem("Sweet and Sour Pork", 9.99);

    // EFFECTS: constructs a menu with various menu items
    public Menu() {
        menu = new ArrayList<>();

        this.addItemToMenu(m1);
        this.addItemToMenu(m2);
        this.addItemToMenu(m3);
        this.addItemToMenu(m4);
    }

    public int getNumberOfItemsOnMenu() {
        return menu.size();
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the ith item on the menu
    public MenuItem getSpecificItem(int i) {
        return menu.get(i - 1);
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the name of the ith item on the menu
    public String getNameOfSpecificItem(int i) {
        return menu.get(i - 1).getMenuItemName();
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the price of the ith item on the menu
    public Double getPriceOfSpecificItem(int i) {
        return menu.get(i - 1).getMenuItemPrice();
    }

    public void addItemToMenu(MenuItem menuItem) {
        menu.add(menuItem);
    }

    public String getItemsOnMenu() {
        int n = 1;
        String nameOfItemsOnMenu = "";

        for (MenuItem menuItem: menu) {
            nameOfItemsOnMenu += n + ". " + getNameOfSpecificItem(n) + " $"
                    + getPriceOfSpecificItem(n);

            if (n != getNumberOfItemsOnMenu()) {
                nameOfItemsOnMenu += "\n";
            }

            n++;
        }

        return nameOfItemsOnMenu;
    }
}
