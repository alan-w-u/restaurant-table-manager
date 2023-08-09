package ui.gui;

import model.*;
import ui.RestaurantTableManagerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Toolbar
public class Toolbar extends JPanel implements ActionListener {
    private RestaurantTableManagerGUI rtm;
    private Restaurant restaurant;

    private JToolBar toolbar;
    private JButton saveButton = new JButton(new ImageIcon("data/icons/save.png"));
    private JButton loadButton = new JButton(new ImageIcon("data/icons/load.png"));
    private JButton addTableButton = new JButton(new ImageIcon("data/icons/add.png"));
    private JButton removeTableButton = new JButton(new ImageIcon("data/icons/remove.png"));

    // MODIFIES: this
    // EFFECTS: constructs the toolbar
    public Toolbar(RestaurantTableManagerGUI rtm, Restaurant restaurant) {
        this.rtm = rtm;
        this.restaurant = restaurant;
        toolbar = new JToolBar("Toolbar");

        initializeToolbar();
        displayToolbar();

        add(toolbar);
    }

    // MODIFIES: this
    // EFFECTS: initializes the Toolbar
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
        addTableButton.setToolTipText("add a table");
        removeTableButton.setToolTipText("remove a table");

        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        addTableButton.addActionListener(this);
        removeTableButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: displays the Toolbar elements
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

    // MODIFIES: this
    // EFFECTS: registers actions performed on the Toolbar
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(saveButton)) {
            rtm.saveRestaurant();
        } else if (e.getSource().equals(loadButton)) {
            rtm.loadRestaurant();
            rtm.refreshToolbar();
        } else if (e.getSource().equals(addTableButton)) {
            if (restaurant.getNumberOfTables() < 35) {
                restaurant.addTable();
            }
        } else if (e.getSource().equals(removeTableButton)) {
            if (restaurant.getNumberOfTables() > 0) {
                restaurant.removeTable();
            }
        }

        rtm.refreshAll();
    }
}
