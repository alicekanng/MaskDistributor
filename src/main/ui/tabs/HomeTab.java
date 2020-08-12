package ui.tabs;

import ui.DistributionListUI;
import ui.listeners.ForeignButtonListener;
import ui.listeners.LocalButtonListener;

import javax.swing.*;
import java.awt.*;

public class HomeTab extends JPanel {
    private static final String GREETING = "Welcome to the Mask Distributor Application!";
    private static final String INSTRUCTIONS = "Please choose which distribution list you would like to load.";
    private JLabel greeting;
    private JLabel instructions;
    private JButton localButton;
    private JButton foreignButton;
    private DistributionListUI ui;

    public HomeTab(DistributionListUI ui) {
        this.ui = ui;

        greeting = new JLabel(GREETING);
        instructions = new JLabel(INSTRUCTIONS);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(greeting, gbc);
        add(instructions, gbc);
        gbc.insets = new Insets(30, 0, 0, 0);
        add(placeListAccessButtons(), gbc);
    }

    //MODIFIES: this
    //EFFECTS: places the two buttons that switch the tab to its according list tab
    private JPanel placeListAccessButtons() {
        localButton = new JButton("Local Distribution List");
        foreignButton = new JButton("Foreign Distribution List");

        JPanel buttonRow = formatButtonRow(localButton, foreignButton);
        buttonRow.setSize(WIDTH, HEIGHT / 2);

        localButton.addActionListener(new LocalButtonListener(localButton, ui));
        foreignButton.addActionListener(new ForeignButtonListener(foreignButton, ui));

        return buttonRow;
    }

    //MODIFIES: this
    //EFFECTS: formats the rows containing the load list buttons
    private JPanel formatButtonRow(JButton b1, JButton b2) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        return buttonPanel;
    }

}
