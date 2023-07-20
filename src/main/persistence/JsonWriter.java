package persistence;

import model.*;
import org.json.JSONObject;
import java.io.*;

// Represents a writer which writes JSON representations of restaurant to file
public class JsonWriter {

    private static final int SPACE = 4;
    private PrintWriter printWriter;
    private String destination;

    // EFFECTS: constructs a writer to write to a destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens printWriter, throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of restaurant to file
    public void write(Restaurant r) {
        JSONObject json = r.toJson();
        saveToFile(json.toString(SPACE));
    }

    // MODIFIES: this
    // EFFECTS: closes printWriter
    public void close() {
        printWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        printWriter.print(json);
    }
}
