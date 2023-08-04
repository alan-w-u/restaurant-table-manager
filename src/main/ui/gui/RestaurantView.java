package ui.gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.*;

// Represents the view of all the tables in the restaurant
public class RestaurantView extends JPanel implements ActionListener {
    private Restaurant restaurant;
    private List<TableIcon> tableIcons = new ArrayList<>();

    private JPanel restaurantPanel;
    private GridBagConstraints constraints;

    private static final int ROW_MAX = 5;

    // EFFECTS: constructs the view of all tables in the restaurant
    public RestaurantView(Restaurant restaurant) {
        this.restaurant = restaurant;

        restaurantPanel = new JPanel();
        constraints = new GridBagConstraints();

        restaurantPanel.setLayout(new GridBagLayout());
        restaurantPanel.setBorder(BorderFactory.createTitledBorder("Restaurant View"));

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;

        displayTables();
//        add(restaurantPanel, constraints);
    }

    // MODIFIES: this
    // EFFECTS: displays the tables on the RestaurantView
    @SuppressWarnings("methodlength")
    public void displayTables() {
        int row = 0;
        int column = 0;
        int tableNumber = 1;

        for (Table t : restaurant.getRestaurantTables()) {

            TableIcon tableIcon = new TableIcon(t, tableNumber);
            tableIcons.add(tableIcon);
            tableIcon.actionListener(this);

            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridx = row;
            constraints.gridy = column;
            constraints.weightx = 0.5;
            constraints.weighty = 0.5;
            constraints.insets = new Insets(5, 5, 5, 5);

            add(tableIcon, constraints, -1);

            if (row + 1 >= ROW_MAX) {
                row = 0;
                column++;
            } else {
                row++;
            }

            tableNumber++;
        }

        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: selects the table to be edited
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click");
    }
}
