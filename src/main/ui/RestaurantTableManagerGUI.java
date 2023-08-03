package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.*;

import model.*;
import ui.guielements.*;

// Restaurant Table Manager GUI Application
public class RestaurantTableManagerGUI extends JFrame {
    private Restaurant restaurant;
    private GridBagConstraints constraints;
    private JPanel restaurantPanel;
    private JPanel tablePanel;

    private Toolbar toolbar;

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int WIDTH = SCREEN_WIDTH / 15 * 10;
    private static final int HEIGHT = SCREEN_HEIGHT / 15 * 10;

    // EFFECTS: constructs the GUI
    public RestaurantTableManagerGUI() {
        restaurant = new Restaurant(0);
        constraints = new GridBagConstraints();

        restaurantPanel = new JPanel();
        tablePanel = new JPanel();

        toolbar = new Toolbar();
//        RestaurantView restaurantView = new RestaurantView(restaurant);

        setTitle("Restaurant Table Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new GridBagLayout());
        centreOnScreen();

        addMouseListener(new DesktopFocusAction());

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

    // MODIFIES: this
    // EFFECTS: generates the restaurant preview with all tables
    private void generateRestaurantView() {
//        restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.PAGE_AXIS));
        restaurantPanel.setLayout(new GridBagLayout());
        restaurantPanel.setBorder(BorderFactory.createTitledBorder("Restaurant View"));

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.6;
        constraints.weighty = 1;

        add(restaurantPanel, constraints);
    }

    // MODIFIES: this
    // EFFECTS: displays the tables in the restaurant
    private void displayTables() {
        for (Table t : restaurant.getRestaurantTables()) {

        }
    }

    // MODIFIES: this
    // EFFECTS: generates the table preview for a specific table
    private void generateTableView() {
        tablePanel.setLayout(new GridBagLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Table View"));

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.4;
        constraints.weighty = 1;

        add(tablePanel, constraints);
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
    // EFFECTS: centres the GUI on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    // EFFECTS: focus on window when mouse is clicked
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            RestaurantTableManagerGUI.this.requestFocusInWindow();
        }
    }

    // EFFECTS: DIRECT ACCESS TO GUI: REMOVE LATER
    public static void main(String[] args) {
        new RestaurantTableManagerGUI();
    }
}
