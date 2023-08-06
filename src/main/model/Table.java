package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

// Represents a restaurant table with a list of orders (menu items) and availability
public class Table implements Writable {

    private List<MenuItem> tableOrder;
    private int tableNumber;
    private int availability;

    // EFFECTS: constructs a table with no orders and is available
    public Table(int tableNumber) {
        tableOrder = new ArrayList<>();
        this.tableNumber = tableNumber;
        availability = 0;
    }

    public List<MenuItem> getTableOrder() {
        return tableOrder;
    }

    public int getTableNumber() {
        return tableNumber;
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
    public void addMenuItem(MenuItem menuItem) {
        if (getAvailability().equals("occupied")) {
            tableOrder.add(menuItem);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the ith item ordered
    public void removeMenuItem(int i) {
        if (!tableOrder.isEmpty()) {
            tableOrder.remove(i - 1);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove an item ordered of given name
    public void removeMenuItem(String menuItem) {
        if (!tableOrder.isEmpty() && getAllItemsOrdered().contains(menuItem)) {
            tableOrder.remove(getNameAllItemsOrdered().lastIndexOf(menuItem));
        }
    }

    // EFFECTS: changes the availability of the table where
    //          0 = available        1 = occupied
    //          2 = ready to pay     3 = needs cleaning
    public void changeAvailability() {
        if (availability <= 2) {
            if (availability == 2) {
                tableOrder = new ArrayList<>();
            }

            availability++;
        } else {
            availability = 0;
        }
    }

    // REQUIRES: 0 <= availability <= 3
    // EFFECTS: changes the availability of the table to
    //          a specific state where
    //          0 = available        1 = occupied
    //          2 = ready to pay     3 = needs cleaning
    public void changeAvailabilityTo(int availability) {
        this.availability = availability;
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

    // EFFECTS: returns the number of unique items ordered
    public int getUniqueItems() {
        int uniqueItems = 0;
        List<MenuItem> seen = new ArrayList<>();

        for (MenuItem m : tableOrder) {
            if (!seen.contains(m)) {
                uniqueItems++;
                seen.add(m);
            }
        }

        return uniqueItems;
    }

    // EFFECTS: returns a unique item and how many times it was ordered
    public String getAllItemsOrdered() {
        String itemsOrdered = "";
        int n = 1;
        int uniqueItems = getUniqueItems();
        List<MenuItem> seen = new ArrayList<>();

        for (MenuItem m1 : tableOrder) {
            int count = 0;
            for (MenuItem m2 : tableOrder) {
                if (seen.contains(m1)) {
                    break;
                } else if (m1.getMenuItemName().equals(m2.getMenuItemName())) {
                    count++;
                }
            }

            if (!seen.contains(m1) && n != uniqueItems) {
                itemsOrdered += m1.getMenuItemName() + " x" + count + "\n";
            }
            if (!seen.contains(m1) && n == uniqueItems) {
                itemsOrdered += m1.getMenuItemName() + " x" + count;
            }

            seen.add(m1);
            n++;
        }

        return itemsOrdered;
    }

    // EFFECTS: returns the total price of all items ordered
    public Double getTotalPriceAllItemsOrdered() {
        int n = 0;
        Double total = 0.0;

        for (MenuItem menuItem : tableOrder) {
            total += getPriceOfSpecificItem(n + 1);
            n++;
        }

        BigDecimal decimalTotal = new BigDecimal(total);
        BigDecimal roundedTotal = decimalTotal.setScale(10, RoundingMode.HALF_UP);

        return roundedTotal.doubleValue();
    }

    // MODIFIES: this
    // EFFECTS: clears the ordered items for the table
    public void resetOrder() {
        tableOrder = new ArrayList<>();
    }

    @Override
    // EFFECTS: converts Table information to JSON format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("table order", menuItemToJson());
        json.put("table number", tableNumber);
        json.put("availability", availability);
        return json;
    }

    // EFFECTS: returns MenuItems in Restaurant as a JSON array
    private JSONArray menuItemToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MenuItem m : tableOrder) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
