package ui;

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
        toolbar = new JToolBar("Toolbar");

        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        initializeToolbar();
        displayToolbar();

        add(toolbar);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getAddTableButton() {
        return addTableButton;
    }

    public JButton getRemoveTableButton() {
        return removeTableButton;
    }

    // MODIFIES: this
    // EFFECTS: initializes the toolbar
    public void initializeToolbar() {
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
    }

    // MODIFIES: this
    // EFFECTS: displays the toolbar elements
    public void displayToolbar() {
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(saveButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(loadButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(addTableButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(removeTableButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
