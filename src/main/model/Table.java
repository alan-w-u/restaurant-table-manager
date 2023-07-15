package model;

import java.util.ArrayList;
import java.util.List;

// Represents a table at a restaurant with...
public class Table {

    private List<MenuItem> tableOrder;
    private int availability;

    public Table() {
        tableOrder = new ArrayList<>();
        availability = 0;
    }

    public List<MenuItem> getTableOrder() {
        return tableOrder;
    }

    public String getAvailability() {
        if (availability == 0) {
            return "available";
        } else if (availability == 1) {
            return "occupied";
        } else if (availability == 2) {
            return "ready to pay";
        } else {
            return "needs cleaning";
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a menu item to a list of ordered item by a table
    public void addMenuItem(MenuItem menuItem) {
        tableOrder.add(menuItem);
    }

    // REQUIRES: 0 <= i <= 3
    // EFFECTS: changes the availability of the table where
    //          0 = available        1 = occupied
    //          2 = ready to pay     3 = needs cleaning
    public void changeAvailabilityTo(int i) {
        this.availability = i;
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the name of the ith item ordered
    public String getNameOfItem(int i) {
        return tableOrder.get(i).getMenuItemName();
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the price of the ith item ordered
    public Double getPriceOfItem(int i) {
        return tableOrder.get(i).getMenuItemPrice();
    }

    // EFFECTS: returns the name of all items ordered
    public List<String> getNameAllItemsOrdered() {
        List<String> nameOfItemsOrdered = new ArrayList<>();

        for (MenuItem menuItem : tableOrder) {
            nameOfItemsOrdered.add(menuItem.getMenuItemName());
        }

        return nameOfItemsOrdered;
    }

    // EFFECTS: returns the price of all items ordered
    public List<Double> getPriceAllItemsOrdered() {
        List<Double> priceOfItemsOrdered = new ArrayList<>();

        for (MenuItem menuItem : tableOrder) {
            priceOfItemsOrdered.add(menuItem.getMenuItemPrice());
        }

        return priceOfItemsOrdered;
    }

}
