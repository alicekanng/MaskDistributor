package ui.tabs;

import model.Customer;
import model.DistributionList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

// Constructs the tab containing the foreign distribution list
public class LocalListTab extends ListTab {
    private JList<String> localList;
    private DefaultListModel<String> localModel;
    private JScrollPane listScrollPane;

    public LocalListTab() {
        super();

        localModel = new DefaultListModel<>();
        localList = new JList<>(localModel);
        listSetUp();
    }

    //MODIFIES: this
    //EFFECTS: adds all the customers from given list into the GUI list
    @Override
    public void addCustomersToListModel(DistributionList list) {
        for (Customer c : list.queue) {
            String entry = c.getName() + NAME_DELIM
                    + c.getAddress() + INFO_DELIM
                    + c.getAge() + INFO_DELIM
                    + c.getConditions();
            localModel.addElement(entry);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up the GUI list
    @Override
    public void listSetUp() {
        localList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        localList.setSelectedIndex(0);
        localList.addListSelectionListener(this);
        localList.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(localList);
        this.add(listScrollPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: if the list is not in the course of being selected and there is no selected index,
    // do not enable the remove customer button, otherwise, enable it
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (localList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    @Override
    public DefaultListModel getListModel() {
        return localModel;
    }

    @Override
    public JList getDistributionList() {
        return localList;
    }
}
