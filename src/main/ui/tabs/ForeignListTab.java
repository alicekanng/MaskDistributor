package ui.tabs;

import model.Customer;
import model.DistributionList;
import ui.listeners.AddListener;
import ui.listeners.RemoveListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class ForeignListTab extends ListTab {
    private JList<String> foreignList;
    private DefaultListModel<String> foreignModel;
    private JScrollPane listScrollPane;

    public ForeignListTab() {
        super();

        foreignModel = new DefaultListModel<String>();
        foreignList = new JList<String>(foreignModel);
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
    //EFFECTS: sets up add customer button
    @Override
    public void addButtonSetUp() {
        addButton = new JButton(ADD_STRING);
        addButton.setActionCommand(ADD_STRING);
        addButton.addActionListener(new AddListener(this));
    }

    //MODIFIES: this
    //EFFECTS: sets up remove customer button
    @Override
    public void removeButtonSetUp() {
        removeButton = new JButton(REMOVE_STRING);
        removeButton.setActionCommand(REMOVE_STRING);
        removeButton.addActionListener(new RemoveListener(this));
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
