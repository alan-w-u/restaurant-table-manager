package ui;

import model.*;
import model.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Table View
public class TableView extends JPanel implements ActionListener {
    private RestaurantTableManagerGUI rtm;
    private Table table;

    private DefaultListModel listModel;
    private JLabel label;
    private JList orderList;
    private JScrollPane scrollPanel;
    private JButton orderItemButton;
    private JButton removeItemButton;
    private JButton checkoutButton;

    // MODIFIES: this
    // EFFECTS: constructs the Table View
    public TableView(RestaurantTableManagerGUI rtm, Table table) {
        this.rtm = rtm;
        this.table = table;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if (this.table != null) {
            label = new JLabel("Table " + table.getTableNumber());
        } else {
            label = new JLabel("Table");
        }

        label.setFont(label.getFont().deriveFont(20f));

        listModel = new DefaultListModel<>();
        orderList = new JList(listModel);
        orderList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        orderList.setSelectedIndex(0);

        scrollPanel = new JScrollPane(orderList);

        orderItemButton = new JButton("Order Item");
        removeItemButton = new JButton("Remove Item");
        checkoutButton = new JButton("Checkout");

        orderItemButton.addActionListener(this);
        removeItemButton.addActionListener(this);
        checkoutButton.addActionListener(this);

        displayTableView();
        generateOrderList();
    }

    // MODIFIES: this
    // EFFECTS: displays the Table View elements
    public void displayTableView() {
        add(Box.createVerticalStrut(rtm.getHeight() / 50));
        add(label);
        add(Box.createVerticalStrut(rtm.getHeight() / 100));
        add(scrollPanel);
        add(orderItemButton);
        add(removeItemButton);
        add(checkoutButton);
    }

    // MODIFIES: this
    // EFFECTS: generates the list of ordered items
    public void generateOrderList() {
//        listModel.addElement("Order for the table:");
        if (table != null) {
            for (MenuItem m : table.getTableOrder()) {
                listModel.addElement(m.getMenuItemName());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: registers actions performed on the Restaurant View
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(orderItemButton)) {
            //
        } else if (e.getSource().equals(removeItemButton)) {
            //
        } else if (e.getSource().equals(checkoutButton)) {
            table.changeAvailability();
        }

        rtm.refreshAll();
    }
}
