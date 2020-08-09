package ui.listeners;

import ui.DistributionListUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocalButtonListener implements ActionListener {
    private JButton localButton;
    private DistributionListUI ui;

    public LocalButtonListener(JButton localButton, DistributionListUI ui) {
        this.localButton = localButton;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        if (buttonPressed.equals(localButton.getText())) {
            ui.getSidebar().setSelectedIndex(DistributionListUI.LOCAL_TAB_INDEX);
        }
    }
}
