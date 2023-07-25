package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader which reads JSON representations of restaurant from file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Restaurant read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurant(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }

        Stream<String> stream = Files.lines(Paths.get(source));
        stream.forEach(s -> contentBuilder.append(s));

        return contentBuilder.toString();
    }

    // EFFECTS: parses Restaurant from JSON object and returns it
    private Restaurant parseRestaurant(JSONObject jsonObject) {
        Restaurant r = new Restaurant(0);
        addTables(r, jsonObject);
        return r;
    }

    // MODIFIES: r
    // EFFECTS: parses Tables from JSON object and adds them to Restaurant
    private void addTables(Restaurant r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("table states");

        for (Object json : jsonArray) {
            JSONObject nextTable = (JSONObject) json;
            addTable(r, nextTable);
        }
    }

    // MODIFIES: r
    // EFFECTS: parses Table information from JSON object and adds it to Restaurant
    private void addTable(Restaurant r, JSONObject jsonObject) {
        int availability = jsonObject.getInt("availability");
        Table t = new Table();
        t.changeAvailabilityTo(availability);
        addTableOrder(r, jsonObject, t);
        r.addSpecificTable(t);
    }

    // MODIFIES: r
    // EFFECTS: parses Table orders from JSON object and adds them to Restaurant
    private void addTableOrder(Restaurant r, JSONObject jsonObject, Table t) {
        JSONArray jsonArray = jsonObject.getJSONArray("table order");

        for (Object json : jsonArray) {
            JSONObject nextTable = (JSONObject) json;
            addMenuItems(r, nextTable, t);
        }
    }

    // MODIFIES: r
    // EFFECTS: parses MenuItems from JSON object and adds it to Restaurant
    private void addMenuItems(Restaurant r, JSONObject jsonObject, Table t) {
        Menu menu = new Menu();

        String name = jsonObject.getString("name");
        t.addMenuItem(menu.getMenuItemsByString(name));
    }
}