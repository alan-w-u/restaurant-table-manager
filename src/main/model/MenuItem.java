package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a menu item with a name and price
public class MenuItem implements Writable {

    private String name;
    private Double price;

    // EFFECTS: constructs a menu item with a name and price
    public MenuItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getMenuItemName() {
        return name;
    }

    public Double getMenuItemPrice() {
        return price;
    }

    public void setMenuItemName(String name) {
        this.name = name;
    }

    public void setMenuItemPrice(Double price) {
        this.price = price;
    }

    @Override
    // EFFECTS: converts MenuItem information to JSON format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        return json;
    }
}
