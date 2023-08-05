package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Represents a single table in the RestaurantView
public class TableIcon extends JPanel implements MouseListener {
    private Table table;

    private JLabel label = new JLabel();
    private JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem menuItem = new JMenuItem();

    // EFFECTS: constructs a table with information
    public TableIcon(Table table, int tableNumber) {
        this.table = table;

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        chooseTableColour();

        addMouseListener(this);

        label.setText("<html><center>Table " + tableNumber + "<br><br><center>" + table.getAvailability());
        menuItem.setText("edit table");

        add(label);
    }

    // MODIFIES: this
    // EFFECTS: chooses the colour of the table based on its availability
    private void chooseTableColour() {
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

    public void actionListener(ActionListener listener) {
        menuItem.addActionListener(listener);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        popupMenu.add(menuItem);
        popupMenu.show(this, e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
