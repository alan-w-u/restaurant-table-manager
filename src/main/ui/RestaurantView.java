package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

// Represents the view of all the tables in the restaurant
public class RestaurantView extends JPanel implements ActionListener {
    private RestaurantTableManagerGUI rtm;
    private Restaurant restaurant;
    private List<TableIcon> tableIcons;

    private JPanel restaurantView;
    private GridBagConstraints constraints;

    private static final int ROW_MAX = 5;

    // MODIFIES: this
    // EFFECTS: constructs the view of all tables in the restaurant
    public RestaurantView(RestaurantTableManagerGUI rtm, Restaurant restaurant) {
        this.rtm = rtm;
        this.restaurant = restaurant;
        tableIcons = new ArrayList<>();

        restaurantView = new JPanel();
        restaurantView.setLayout(new GridBagLayout());

        add(displayTables());
    }

    // MODIFIES: this
    // EFFECTS: displays the tables on the RestaurantView
    public JPanel displayTables() {
        int row = 0;
        int column = 1;

        noTables();

        for (Table t : restaurant.getRestaurantTables()) {
            TableIcon tableIcon = new TableIcon(rtm, t);
            tableIcons.add(tableIcon);

            constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.NONE;
            constraints.gridx = row;
            constraints.gridy = column;
            constraints.insets = new Insets(5, 5, 5, 5);

            restaurantView.add(tableIcon, constraints, -1);

            if (row + 1 >= ROW_MAX) {
                row = 0;
                column++;
            } else {
                row++;
            }
        }

        return restaurantView;
    }

    // MODIFIES: this
    // EFFECTS: adds a no table label if restaurant is empty
    public void noTables() {
        if (restaurant.getRestaurantTables().isEmpty()) {
            JLabel label = new JLabel("NO TABLES");
            label.setFont(label.getFont().deriveFont(20f));

            restaurantView.add(label);
        }
    }

    // MODIFIES: this
    // EFFECTS: selects the table to be edited
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
