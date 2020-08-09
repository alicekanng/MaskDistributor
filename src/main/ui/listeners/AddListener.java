package ui.listeners;

import ui.tabs.ListTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {
    private JButton addButton;
    private ListTab listTab;
    private boolean alreadyEnabled;

    public AddListener(JButton addButton, ListTab listTab) {
        this.addButton = addButton;
        this.listTab = listTab;
        alreadyEnabled = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entry = listTab.getCustomerEntry().getText();
        if (!entry.equals("")) {
            int index = listTab.getDistributionList().getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            listTab.getListModel().insertElementAt(entry, index);
            listTab.getCustomerEntry().requestFocusInWindow();
            listTab.getCustomerEntry().setText("");
        }
    }
}

