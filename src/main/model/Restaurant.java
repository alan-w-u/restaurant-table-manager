package model;

import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

// Represents a restaurant with tables
public class Restaurant implements Writable {

    private List<Table> restaurantTables;
    private int numberOfTables;

    // EFFECTS: constructs a restaurant with a given amount of tables
    public Restaurant(int numberOfTables) {
        restaurantTables = new ArrayList<>();
        this.numberOfTables = numberOfTables;

        for (int i = 0; i < numberOfTables; i++) {
            restaurantTables.add(new Table());
        }
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    // REQUIRES: i > 0
    // EFFECTS: returns the ith table in the restaurant
    public Table getSpecificTable(int i) {
        return restaurantTables.get(i - 1);
    }

    // MODIFIES: this
    // EFFECTS: adds another table to the restaurant
    public void addTable() {
        restaurantTables.add(new Table());
        numberOfTables++;
    }

    public void removeTable() {
        restaurantTables.remove(this.getNumberOfTables() - 1);
        numberOfTables--;
    }

    // REQUIRES: x > 0
    // MODIFIES: this
    // EFFECTS: adds a given amount of tables to a restaurant
    //          starting from the end
    public void addAmountOfTables(int x) {
        for (int i = 0; i < x; i++) {
            addTable();
        }
    }

    // REQUIRES: x > 0
    // MODIFIES: this
    // EFFECTS: remove a given amount of tables to a restaurant
    //          starting from the last one added
    public void removeAmountOfTables(int x) {
        for (int i = 0; i < x; i++) {
            removeTable();
        }
    }

    // EFFECTS: returns numbered tables with their corresponding availability
    public String getAllAvailability() {
        int n = 1;
        String allAvailability = "";

        for (Table t : restaurantTables) {
            allAvailability += "Table " + n + ": " + t.getAvailability();

            if (n != restaurantTables.size()) {
                allAvailability += "\n";
            }

            n++;
        }
        return allAvailability;
    }

    @Override
    // EFFECTS: converts Restaurant information to JSON format
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("number of tables", numberOfTables);
        json.put("table status", tableToJson());
        return json;
    }

    // EFFECTS: returns Tables in Restaurant as a JSON array
    private JSONArray tableToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Table t : restaurantTables) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
