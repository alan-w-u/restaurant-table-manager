package ui.guielements;

import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class RestaurantView extends JPanel {
    private Restaurant restaurant;
    private JPanel restaurantPanel;
    private GridBagConstraints constraints;

    private Border borderTitle;
    private Border borderSpacer;

    // EFFECTS: constructs the view of all tables in the restaurant
    public RestaurantView(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurantPanel = new JPanel();
        constraints = new GridBagConstraints();

        restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.PAGE_AXIS));


        borderTitle = BorderFactory.createTitledBorder("Restaurant Preview");
        borderSpacer = BorderFactory.createEmptyBorder(100, 100, 100, 100);

        JPanel restaurantPanelBox = new JPanel();
        restaurantPanelBox.setLayout(new GridBagLayout());
        restaurantPanelBox.setBorder(BorderFactory.createCompoundBorder(borderTitle, borderSpacer));
        add(restaurantPanelBox);

//        JPanel rp = new JPanel();
//        rp.setLayout(new BoxLayout(rp, BoxLayout.PAGE_AXIS));
//        rp.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createTitledBorder(
//                        "Preview"),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//        rp.add(restaurantPanel);
    }

    private void setupRestaurantView() {

    }
}
