package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// Restaurant Table Manager GUI Application
public class RestaurantTableManagerGUI extends JFrame implements ActionListener, MouseListener {
    private Restaurant restaurant;
    private static Table currentTable;

    private GridBagConstraints constraints;
    private Toolbar toolbar;
    private RestaurantView restaurantView;
    private TableView tableView;
    private JPanel restaurantPanel;
    private JPanel tablePanel;

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static int WIDTH = SCREEN_WIDTH / 15 * 10;
    private static final int HEIGHT = SCREEN_HEIGHT / 15 * 10;

    private static final String JSON_LOCATION = "./data/restaurant.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs the Restaurant Table Manager GUI
    public RestaurantTableManagerGUI() {
        restaurant = new Restaurant(0);
        currentTable = null;

        toolbar = new Toolbar(this, restaurant);
        restaurantView = new RestaurantView(this, restaurant);
        tableView = new TableView(this, currentTable);

        restaurantPanel = new JPanel();
        tablePanel = new JPanel();

        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        addMouseListener(this);

        setTitle("Restaurant Table Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocation((SCREEN_WIDTH - WIDTH) / 2, (SCREEN_HEIGHT - HEIGHT) / 2);
        setLayout(new GridBagLayout());

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

        refreshAll();
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

        refreshAll();
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
        toolbar = new Toolbar(this, restaurant);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0;
        constraints.weighty = 1;

        add(toolbar, constraints);
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
        constraints.weightx = 0.8;
        constraints.weighty = 1;

        add(restaurantPanel, constraints);
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
        constraints.weightx = 0.2;
        constraints.weighty = 1;

        add(tablePanel, constraints);
    }

    public void setCurrentTable(Table table) {
        currentTable = table;
    }

    // MODIFIES: this
    // EFFECTS: refreshes all screen elements
    public void refreshAll() {
        refreshRestaurantView();
        refreshTableView();
    }

    // MODIFIES: this
    // EFFECTS: refreshes the Restaurant View to update changes
    public void refreshRestaurantView() {
        restaurantPanel.remove(restaurantView);
        restaurantView = new RestaurantView(this, restaurant);
        restaurantPanel.add(restaurantView);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: refreshes the Table View to update changes
    public void refreshTableView() {
        tablePanel.remove(tableView);
        tableView = new TableView(this, currentTable);
        tablePanel.add(tableView);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // EFFECTS: refreshes screen when mouse pressed
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // EFFECTS: DIRECT ACCESS TO GUI: REMOVE LATER
    public static void main(String[] args) {
        new RestaurantTableManagerGUI();
    }
}
