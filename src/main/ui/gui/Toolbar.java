package ui.gui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the toolbar
public class Toolbar extends JPanel implements ActionListener {
    private Restaurant restaurant;

    private static final String JSON_LOCATION = "./data/restaurant.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JToolBar toolbar;
    private JButton saveButton = new JButton(new ImageIcon("data/icons/save.png"));
    private JButton loadButton = new JButton(new ImageIcon("data/icons/load.png"));
    private JButton addTableButton = new JButton(new ImageIcon("data/icons/add.png"));
    private JButton removeTableButton = new JButton(new ImageIcon("data/icons/remove.png"));

    // MODIFIES: this
    // EFFECTS: constructs the toolbar
    public Toolbar(Restaurant restaurant) {
        this.restaurant = restaurant;
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        toolbar = new JToolBar("Toolbar");

        initializeToolbar();
        displayToolbar();

        add(toolbar);
    }

    // MODIFIES: this
    // EFFECTS: initializes the toolbar
    private void initializeToolbar() {
        toolbar.setOrientation(SwingConstants.VERTICAL);
        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        saveButton.setBorderPainted(false);
        loadButton.setBorderPainted(false);
        addTableButton.setBorderPainted(false);
        removeTableButton.setBorderPainted(false);

        saveButton.setToolTipText("save");
        loadButton.setToolTipText("load");
        addTableButton.setToolTipText("add tables");
        removeTableButton.setToolTipText("remove tables");

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        addTableButton.addActionListener(this);
        removeTableButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: displays the toolbar elements
    private void displayToolbar() {
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(saveButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(loadButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(addTableButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(removeTableButton);
    }

    // MODIFIES: this
    // EFFECTS: determines action when buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            try {
                jsonWriter.open();
                jsonWriter.write(restaurant);
                jsonWriter.close();
                System.out.println("Saved restaurant to " + JSON_LOCATION);
            } catch (FileNotFoundException x) {
                System.out.println("Unable to write to file: " + JSON_LOCATION);
            }
        } else if (e.getSource().equals(loadButton)) {
            try {
                restaurant = jsonReader.read();
            } catch (IOException x) {
                System.out.println("Unable to read from file: " + JSON_LOCATION);
            }
        } else if (e.getSource().equals(addTableButton)) {
            restaurant.addTable();
        } else if (e.getSource().equals(removeTableButton)) {
            restaurant.removeTable();
        }
    }
}
