package ui;

import model.*;
import model.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableView extends JPanel implements ActionListener {
    private Table table;

    private DefaultListModel listModel;
    private JList orderList;
    private JScrollPane scrollPanel;
    private JButton orderButton;
    private JButton checkoutButton;
    private JButton changeAvailabilityButton;

    public TableView(Table table) {
        this.table = table;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        listModel = new DefaultListModel<>();
        orderList = new JList(listModel);
        orderList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        orderList.setSelectedIndex(0);

        scrollPanel = new JScrollPane(orderList);

        orderButton = new JButton("Order");
        checkoutButton = new JButton("Checkout");
        changeAvailabilityButton = new JButton("Change Availability");

        orderButton.addActionListener(this);
        checkoutButton.addActionListener(this);
        changeAvailabilityButton.addActionListener(this);

        add(scrollPanel);
        add(orderButton);
        add(checkoutButton);
        add(changeAvailabilityButton);

        generateOrderList();
    }

    // MODIFIES: this
    // EFFECTS: generates the list of ordered items
    public void generateOrderList() {
        if (table != null) {
            for (MenuItem m : table.getTableOrder()) {
                listModel.addElement(m.getMenuItemName());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: selects the table to be edited
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(orderButton)) {
            //
        } else if (e.getSource().equals(checkoutButton)) {
            //
        } else if (e.getSource().equals(changeAvailabilityButton)) {
            //
        }
    }
}
