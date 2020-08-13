package ui.tabs;

import model.Customer;
import model.DistributionList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

// Constructs the tab containing the foreign distribution list
public class ForeignListTab extends ListTab {
    private JList<String> foreignList;
    private DefaultListModel<String> foreignModel;
    private JScrollPane listScrollPane;

    public ForeignListTab() {
        super();

        foreignModel = new DefaultListModel<>();
        foreignList = new JList<>(foreignModel);
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
            foreignModel.addElement(entry);
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up the GUI list
    public void listSetUp() {
        foreignList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        foreignList.setSelectedIndex(0);
        foreignList.addListSelectionListener(this);
        foreignList.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(foreignList);
        this.add(listScrollPane, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: if the list is not in the course of being selected and there is no selected index,
    // do not enable the remove customer button, otherwise, enable it
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (foreignList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    public DefaultListModel getListModel() {
        return foreignModel;
    }

    public JList getDistributionList() {
        return foreignList;
    }
}
