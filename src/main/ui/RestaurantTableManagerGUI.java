package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.*;

// Restaurant Table Manager GUI Application
public class RestaurantTableManagerGUI extends JFrame {
    private Restaurant restaurant;
    private JPanel tablePanel;
    private JPanel controlPanel;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JToolBar toolbar;

    public RestaurantTableManagerGUI() {
        super("Restaurant Table Manager");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Restaurant Table Manager");
        setSize(WIDTH, HEIGHT);
        centreOnScreen();
        setLayout(new BorderLayout());
        setVisible(true);

        addMouseListener(new DesktopFocusAction());
        addToolbar();
    }

    // EFFECTS: adds a toolbar
    private void addToolbar() {
        toolbar = new JToolBar("Toolbar");
        toolbar.setOrientation(SwingConstants.VERTICAL);

        JButton saveButton = new JButton(new ImageIcon("data/icons/save.png"));
        JButton loadButton = new JButton(new ImageIcon("data/icons/load.png"));
        JButton addTableButton = new JButton(new ImageIcon("data/icons/add.png"));
        JButton removeTableButton = new JButton(new ImageIcon("data/icons/remove.png"));

        toolbar.add(saveButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(loadButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(addTableButton);
        toolbar.add(Box.createVerticalStrut(5));
        toolbar.add(removeTableButton);

        saveButton.setToolTipText("save");
        loadButton.setToolTipText("load");
        addTableButton.setToolTipText("add tables");
        removeTableButton.setToolTipText("remove tables");

        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        add(toolbar, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: centres the application on screen when run
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // EFFECTS: focus on window when mouse is clicked
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            RestaurantTableManagerGUI.this.requestFocusInWindow();
        }
    }

    // EFFECTS: DIRECT ACCESS TO GUI REMOVE LATER
    public static void main(String[] args) {
        new RestaurantTableManagerGUI();
    }
}
