package ui.tabs;

import ui.DistributionListUI;
import ui.listeners.ForeignButtonListener;
import ui.listeners.LocalButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeTab extends JPanel {
    private static final String GREETING = "Welcome to the Mask Distributor Application!";
    private JLabel greeting;
    private JButton localButton;
    private JButton foreignButton;
    private DistributionListUI ui;

    public HomeTab(DistributionListUI ui) {
        this.ui = ui;
        setLayout(new GridLayout(2, 1));

        placeGreeting();
        placeListAccessButtons();
    }

    private void placeGreeting() {
        greeting = new JLabel(GREETING, JLabel.CENTER);
        this.add(greeting);
    }

    private void placeListAccessButtons() {
        localButton = new JButton("Local Distribution List");
        foreignButton = new JButton("Foreign Distribution List");

        JPanel buttonRow = formatButtonRow(localButton, foreignButton);
        buttonRow.setSize(WIDTH, HEIGHT / 2);

        localButton.addActionListener(new LocalButtonListener(localButton, ui));
        foreignButton.addActionListener(new ForeignButtonListener(foreignButton, ui));

        this.add(buttonRow);
    }

    private JPanel formatButtonRow(JButton b1, JButton b2) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        return buttonPanel;
    }

}
