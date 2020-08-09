package ui.listeners;

import ui.DistributionListUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForeignButtonListener implements ActionListener {
    private JButton foreignButton;
    private DistributionListUI ui;

    public ForeignButtonListener(JButton foreignButton, DistributionListUI ui) {
        this.foreignButton = foreignButton;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        if (buttonPressed.equals(foreignButton.getText())) {
            ui.getSidebar().setSelectedIndex(DistributionListUI.FOREIGN_TAB_INDEX);
        }
    }
}
