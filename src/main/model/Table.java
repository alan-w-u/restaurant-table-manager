package model;

import java.util.ArrayList;
import java.util.List;

// Represents a restaurant table with a list of orders (menu items) and availability
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

    // REQUIRES: table availability is "occupied"
    // MODIFIES: this
    // EFFECTS: adds a menu item to a list of ordered item by a table
    //          and return true if successfully added and false otherwise
    public boolean addMenuItem(MenuItem menuItem) {
        if (this.getAvailability().equals("occupied")) {
            tableOrder.add(menuItem);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: changes the availability of the table where
    //          0 = available        1 = occupied
    //          2 = ready to pay     3 = needs cleaning
    public void changeAvailability() {
        if (this.availability <= 2) {
            this.availability++;
        } else {
            this.availability = 0;
        }
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the name of the ith item ordered
    public String getNameOfSpecificItem(int i) {
        return tableOrder.get(i - 1).getMenuItemName();
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the price of the ith item ordered
    public Double getPriceOfSpecificItem(int i) {
        return tableOrder.get(i - 1).getMenuItemPrice();
    }

    public int getNumberOfItemsOrdered() {
        return tableOrder.size();
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

    // EFFECTS: returns the total price of all items ordered
    public Double getTotalPriceAllItemsOrdered() {
        int n = 0;
        Double total = 0.0;

        for (MenuItem menuItem : tableOrder) {
            total += this.getPriceOfSpecificItem(n + 1);
            n++;
        }

        return total;
    }

}
