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
    private Restaurant restaurant;
    private List<TableIcon> tableIcons;

    private JPanel restaurantView;
    private GridBagConstraints constraints;

    private static final int ROW_MAX = 5;

    // EFFECTS: constructs the view of all tables in the restaurant
    public RestaurantView(Restaurant restaurant) {
        this.restaurant = restaurant;

        restaurantView = new JPanel();
        restaurantView.setLayout(new GridBagLayout());

        add(displayTables());
    }

    // MODIFIES: this
    // EFFECTS: displays the tables on the RestaurantView
    @SuppressWarnings("methodlength")
    public JPanel displayTables() {
        int row = 0;
        int column = 1;
        int tableNumber = 1;

        tableIcons = new ArrayList<>();

        for (Table t : restaurant.getRestaurantTables()) {
            TableIcon tableIcon = new TableIcon(t, tableNumber);
            tableIcons.add(tableIcon);
            tableIcon.actionListener(this);

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

            tableNumber++;
        }

        return restaurantView;
    }

    // MODIFIES: this
    // EFFECTS: selects the table to be edited
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
