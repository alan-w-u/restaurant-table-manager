package ui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the Table View
public class TableView extends JPanel implements ActionListener {
    private RestaurantTableManagerGUI rtm;
    private Table table;
    private Menu menu;

    private JLabel menuLabel;
    private JLabel tableLabel;
    private JLabel priceLabel;
    private DefaultListModel menuListModel;
    private DefaultListModel orderListModel;
    private JList menuList;
    private JList orderList;
    private JScrollPane menuScrollPanel;
    private JScrollPane orderScrollPanel;
    private JButton orderItemButton;
    private JButton removeItemButton;
    private JButton checkoutButton;

    // MODIFIES: this
    // EFFECTS: constructs the Table View
    public TableView(RestaurantTableManagerGUI rtm, Table table) {
        this.rtm = rtm;
        this.table = table;

        menu = new Menu();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if (this.table != null) {
            tableLabel = new JLabel("Table " + table.getTableNumber());
            priceLabel = new JLabel("Total: $" + table.getTotalPriceAllItemsOrdered());
        } else {
            tableLabel = new JLabel("Table");
            priceLabel = new JLabel("Total: $0.0");
        }

        menuLabel = new JLabel("Menu");

        menuListModel = new DefaultListModel<>();
        menuList = new JList(menuListModel);
        menuScrollPanel = new JScrollPane(menuList);

        orderListModel = new DefaultListModel<>();
        orderList = new JList(orderListModel);
        orderScrollPanel = new JScrollPane(orderList);

        initializeTableView();
        displayTableView();
        generateMenuList();
        generateOrderList();
    }

    // MODIFIES: this
    // EFFECTS: initializes the Table View
    public void initializeTableView() {
        menuLabel.setFont(tableLabel.getFont().deriveFont(20f));
        tableLabel.setFont(tableLabel.getFont().deriveFont(20f));

        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setSelectedIndex(0);

        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setSelectedIndex(0);

        orderItemButton = new JButton("Order Item");
        removeItemButton = new JButton("Remove Item");
        checkoutButton = new JButton("Checkout");

        orderItemButton.addActionListener(this);
        removeItemButton.addActionListener(this);
        checkoutButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: displays the Table View elements
    public void displayTableView() {
        add(Box.createVerticalStrut(rtm.getHeight() / 100));

        add(menuLabel);
        add(Box.createVerticalStrut(rtm.getHeight() / 100));
        add(menuScrollPanel);

        add(Box.createVerticalStrut(rtm.getHeight() / 50));

        add(tableLabel);
        add(Box.createVerticalStrut(rtm.getHeight() / 100));
        add(orderScrollPanel);

        add(Box.createVerticalStrut(rtm.getHeight() / 100));

        add(priceLabel);

        add(Box.createVerticalStrut(rtm.getHeight() / 50));

        add(orderItemButton);
        add(removeItemButton);
        add(checkoutButton);
    }

    // MODIFIES: this
    // EFFECTS: generates the menu list
    public void generateMenuList() {
        for (MenuItem m : menu.getMenu()) {
            menuListModel.addElement(m.getMenuItemName() + " [$" + m.getMenuItemPrice() + "]");
        }
    }

    // MODIFIES: this
    // EFFECTS: generates the list of ordered items
    public void generateOrderList() {
        if (table != null) {
            if (table.getAvailability().equals("occupied")) {
                orderListModel.addElement("ORDER FOR THE TABLE:");
                orderListModel.addElement("-----------------");
                for (MenuItem m : table.getTableOrder()) {
                    orderListModel.addElement(m.getMenuItemName());
                }
            } else if (table.getAvailability().equals("ready to pay")) {
                orderListModel.addElement("ORDER FOR THE TABLE:");
                orderListModel.addElement("-----------------");
                for (MenuItem m : table.getTableOrder()) {
                    orderListModel.addElement(m.getMenuItemName());
                }
                orderListModel.addElement("-----------------");
                orderListModel.addElement("TOTAL PRICE: $" + table.getTotalPriceAllItemsOrdered());
            } else {
                orderListModel.addElement("NO ORDERS");
            }
        } else {
            orderListModel.addElement("NO ORDERS");
        }
    }

    // MODIFIES: this
    // EFFECTS: registers actions performed on the Restaurant View
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(orderItemButton) && !table.getAvailability().equals("ready to pay")) {
            if (table.getAvailability().equals("available")) {
                table.changeAvailabilityTo(1);
            }
            table.addMenuItem(menu.getSpecificItem(menuList.getSelectedIndex() + 1));
        } else if (e.getSource().equals(removeItemButton)) {
            if (orderList.getSelectedIndex() > 1) {
                table.removeMenuItem(orderList.getSelectedIndex() - 1);
            }
        } else if (e.getSource().equals(checkoutButton)) {
            table.changeAvailability();
        }

        generateOrderList();
        rtm.refreshAll();
    }
}
