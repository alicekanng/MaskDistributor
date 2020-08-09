package ui.tabs;

import model.Customer;
import model.DistributionList;
import ui.listeners.AddListener;
import ui.listeners.RemoveListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class ForeignListTab extends ListTab {
    private JList foreignList;
    private DefaultListModel foreignModel;
    private JScrollPane listScrollPane;

    public ForeignListTab() {
        super();

        foreignModel = new DefaultListModel();
        foreignList = new JList(foreignModel);
        listSetUp();
    }

    @Override
    public void addCustomersToListModel(DistributionList list) {
        for (Customer c : list.queue) {
            String entry = c.getName() + NAME_DELIM
                    + c.getAge() + INFO_DELIM
                    + c.getAddress() + INFO_DELIM
                    + c.getConditions();
            foreignModel.addElement(entry);
        }
    }

    public void listSetUp() {
        foreignList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        foreignList.setSelectedIndex(0);
        foreignList.addListSelectionListener(this);
        foreignList.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(foreignList);
        this.add(listScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void addButtonSetUp() {
        addButton = new JButton(ADD_STRING);
        addButton.setActionCommand(ADD_STRING);
        addButton.addActionListener(new AddListener(addButton, this));
    }

    @Override
    public void removeButtonSetUp() {
        removeButton = new JButton(REMOVE_STRING);
        removeButton.setActionCommand(REMOVE_STRING);
        removeButton.addActionListener(new RemoveListener(this));
    }

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
