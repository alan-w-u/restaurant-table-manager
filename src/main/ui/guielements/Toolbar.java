package ui.guielements;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {

    private JToolBar toolbar;

    private JButton saveButton = new JButton(new ImageIcon("data/icons/save.png"));
    private JButton loadButton = new JButton(new ImageIcon("data/icons/load.png"));
    private JButton addTableButton = new JButton(new ImageIcon("data/icons/add.png"));
    private JButton removeTableButton = new JButton(new ImageIcon("data/icons/remove.png"));

    // EFFECTS: adds a toolbar
    public Toolbar() {
        toolbar = new JToolBar("Toolbar");
        toolbar.setOrientation(SwingConstants.VERTICAL);
        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        saveButton.setBorderPainted(false);
        loadButton.setBorderPainted(false);
        addTableButton.setBorderPainted(false);
        removeTableButton.setBorderPainted(false);

        toolbar.add(Box.createVerticalStrut(5));
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
        add(toolbar);
    }
}
