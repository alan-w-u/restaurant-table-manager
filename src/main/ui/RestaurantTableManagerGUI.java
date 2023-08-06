package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// Restaurant Table Manager GUI Application
public class RestaurantTableManagerGUI extends JFrame implements ActionListener {
    private Restaurant restaurant;
    private GridBagConstraints constraints;
    private JPanel restaurantPanel;
    private JPanel tablePanel;
    private Toolbar toolbar;
    private RestaurantView restaurantView;
    private TableView tableView;

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int WIDTH = SCREEN_WIDTH / 15 * 10;
    private static final int HEIGHT = SCREEN_HEIGHT / 15 * 10;

    private static final String JSON_LOCATION = "./data/restaurant.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs the Restaurant Table Manager GUI
    public RestaurantTableManagerGUI() {
        restaurant = new Restaurant(0);

        toolbar = new Toolbar(restaurant);
        restaurantPanel = new JPanel();
        tablePanel = new JPanel();

        restaurantView = new RestaurantView(restaurant);
        tableView = new TableView(null);

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
    public void generateLayout() {
        generateToolbar();
        generateRestaurantView();
        generateTableView();

        revalidate();
        repaint();
    }

    // EFFECTS: ask whether to load a saved restaurant or create a new one
    public void askLoadOrCreate() {
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

    // MODIFIES: this
    // EFFECTS: generates a Restaurant with a given amount of tables
    public void generateRestaurant() {
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
    public void generateToolbar() {
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 1;

        add(toolbar, constraints);
        toolbar.getSaveButton().addActionListener(this);
        toolbar.getLoadButton().addActionListener(this);
        toolbar.getAddTableButton().addActionListener(this);
        toolbar.getRemoveTableButton().addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: generates the restaurant preview with all tables
    public void generateRestaurantView() {
        restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.PAGE_AXIS));
        restaurantPanel.setBorder(BorderFactory.createTitledBorder("Restaurant View"));

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        restaurantPanel.add(restaurantView, constraints);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;

        add(restaurantPanel, constraints);
    }

    // MODIFIES: this
    // EFFECTS: refreshes the Restaurant View to update changes
    private void refreshRestaurantView() {
        restaurantPanel.remove(restaurantView);
        restaurantView = new RestaurantView(restaurant);
        restaurantPanel.add(restaurantView);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: generates the table preview for a specific table
    public void generateTableView() {
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Table View"));

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        tablePanel.add(tableView);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 1;

        add(tablePanel, constraints);
    }

    // MODIFIES: this
    // EFFECTS: centres the GUI on screen
    public void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: registers actions performed on the toolbar
    public void toolbarActionPerformed(ActionEvent e) {
        if (e.getSource().equals(toolbar.getSaveButton())) {
            try {
                jsonWriter.open();
                jsonWriter.write(restaurant);
                jsonWriter.close();
                refreshRestaurantView();
            } catch (FileNotFoundException x) {
                //
            }
        } else if (e.getSource().equals(toolbar.getLoadButton())) {
            try {
                restaurant = jsonReader.read();
                refreshRestaurantView();
            } catch (IOException x) {
                //
            }
        } else if (e.getSource().equals(toolbar.getAddTableButton())) {
            restaurant.addTable();
            refreshRestaurantView();
        } else if (e.getSource().equals(toolbar.getRemoveTableButton())) {
            restaurant.removeTable();
            refreshRestaurantView();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolbarActionPerformed(e);
    }

    // EFFECTS: DIRECT ACCESS TO GUI: REMOVE LATER
    public static void main(String[] args) {
        new RestaurantTableManagerGUI();
    }
}
