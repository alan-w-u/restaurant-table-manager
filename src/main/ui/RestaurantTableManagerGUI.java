package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import model.*;
import ui.gui.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// Restaurant Table Manager GUI Application
public class RestaurantTableManagerGUI extends JFrame implements ActionListener {
    private Restaurant restaurant;
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel restaurantPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private Toolbar toolbar;
    private RestaurantView restaurantView;

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int WIDTH = SCREEN_WIDTH / 15 * 10;
    private static final int HEIGHT = SCREEN_HEIGHT / 15 * 10;

    private static final String JSON_LOCATION = "./data/restaurant.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs the GUI
    public RestaurantTableManagerGUI() {
        restaurant = new Restaurant(0);
        toolbar = new Toolbar(restaurant);
        restaurantView = new RestaurantView(restaurant);

        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        setTitle("Restaurant Table Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new GridBagLayout());
        centreOnScreen();

        askLoadOrCreate();
        generateLayout();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: generates the layout of the GUI
    private void generateLayout() {
        generateRestaurantView();
        generateTableView();
        generateToolbar();

//        add(toolbar, constraints);
//        add(restaurantView, constraints);
    }

    // EFFECTS: ask whether to load a saved restaurant or create a new one
    private void askLoadOrCreate() {
        int input  = JOptionPane.showConfirmDialog(
                this,
                "Do you want to load the saved restaurant?",
                "Create or Load Restaurant",
                JOptionPane.YES_NO_OPTION);

        if (input == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        } else if (input == JOptionPane.YES_OPTION) {
            try {
                restaurant = jsonReader.read();
            } catch (IOException e) {
                generateRestaurant();
            }
        } else {
            generateRestaurant();
        }

        restaurantView = new RestaurantView(restaurant);
    }

    // EFFECTS: generates a Restaurant with a given amount of tables
    private void generateRestaurant() {
        try {
            int input = Integer.parseInt(JOptionPane.showInputDialog(
                    this,
                    "Enter the number of tables (integer) in the restaurant:",
                    "Generate Restaurant",
                    JOptionPane.PLAIN_MESSAGE));

            if (input == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            } else {
                restaurant.addAmountOfTables(input);
            }
        } catch (Exception e) {
            askLoadOrCreate();
        }
    }

    // MODIFIES: this
    // EFFECTS: generates the toolbar
    private void generateToolbar() {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 1;

        add(toolbar, constraints);
    }

    // MODIFIES: this
    // EFFECTS: generates the restaurant preview with all tables
    private void generateRestaurantView() {
        restaurantPanel.setLayout(new GridBagLayout());
        restaurantPanel.setBorder(BorderFactory.createTitledBorder("Restaurant View"));

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.weighty = 1;

        add(restaurantPanel, constraints);
//        add(restaurantView, constraints);
    }

    // MODIFIES: this
    // EFFECTS: generates the table preview for a specific table
    private void generateTableView() {
        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Table View"));

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.weighty = 1;

        add(tablePanel, constraints);
    }

    // MODIFIES: this
    // EFFECTS: centres the GUI on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(restaurant.getNumberOfTables());

    }

    // EFFECTS: DIRECT ACCESS TO GUI: REMOVE LATER
    public static void main(String[] args) {
        new RestaurantTableManagerGUI();
    }
}
