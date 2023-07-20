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

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Restaurant from JSON object and returns it
    private Restaurant parseRestaurant(JSONObject jsonObject) {
        int numberOfTables = jsonObject.getInt("number of tables");
        Restaurant r = new Restaurant(numberOfTables);
//        addTables(r, jsonObject);
        return r;
    }

//    // MODIFIES: r
//    // EFFECTS: parses tables from JSON object and adds them to Restaurant
//    private void addTables(Restaurant r, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("table order");
//
//        for (Object json : jsonArray) {
//            JSONObject nextThingy = (JSONObject) json;
//            addThingy(r, nextThingy);
//        }
//    }
//
//    // MODIFIES: r
//    // EFFECTS: parses thingy from JSON object and adds it to workroom
//    private void addThingy(WorkRoom wr, JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        Category category = Category.valueOf(jsonObject.getString("category"));
//        Thingy thingy = new Thingy(name, category);
//        wr.addThingy(thingy);
//    }
}
