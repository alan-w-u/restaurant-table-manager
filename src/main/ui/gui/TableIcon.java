package ui.gui;

import model.*;
import ui.RestaurantTableManagerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Represents a single table in the RestaurantView
public class TableIcon extends JPanel implements MouseListener {
    private RestaurantTableManagerGUI rtm;
    private Table table;
    private int tableNumber;

    private JLabel label = new JLabel();

    // MODIFIES: this
    // EFFECTS: constructs a table with information
    public TableIcon(RestaurantTableManagerGUI rtm, Table table) {
        this.rtm = rtm;
        this.table = table;
        this.tableNumber = table.getTableNumber();

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        chooseTableColour();

        addMouseListener(this);

        label.setText("<html><center>Table " + tableNumber + "<br><br><center>" + table.getAvailability());

        add(label);
    }

    // MODIFIES: this
    // EFFECTS: chooses the colour of the table based on its availability
    public void chooseTableColour() {
        if (table.getAvailability().equals("available")) {
            setBackground(new Color(0xccffe5));
        } else if (table.getAvailability().equals("occupied")) {
            setBackground(new Color(0xffcccc));
        } else if (table.getAvailability().equals("ready to pay")) {
            setBackground(new Color(0xffffcc));
        } else {
            setBackground(new Color(0xe5ccff));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    // EFFECTS: shifts the Table View to table that is clicked
    @Override
    public void mousePressed(MouseEvent e) {
        rtm.setCurrentTable(table);
        rtm.refreshAll();
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
}
