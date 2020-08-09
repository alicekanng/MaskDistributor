package ui.listeners;

import ui.tabs.ListTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveListener implements ActionListener {
    private ListTab listUI;

    public RemoveListener(ListTab listUI) {
        this.listUI = listUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = listUI.getDistributionList().getSelectedIndex();
        if (index != -1) {
            listUI.getListModel().remove(index);
            listUI.getDistributionList().setSelectedIndex(index);
            listUI.getDistributionList().ensureIndexIsVisible(index);
        }
    }
}
