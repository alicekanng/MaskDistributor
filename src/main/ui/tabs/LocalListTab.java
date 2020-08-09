package ui.tabs;

import model.Customer;
import model.DistributionList;
import ui.listeners.AddListener;
import ui.listeners.RemoveListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class LocalListTab extends ListTab {
    private JList localList;
    private DefaultListModel localModel;
    private JScrollPane listScrollPane;

    public LocalListTab() {
        super();

        localModel = new DefaultListModel();
        localList = new JList(localModel);
        listSetUp();
    }

    @Override
    public void addCustomersToListModel(DistributionList list) {
        for (Customer c : list.queue) {
            String entry = c.getName() + NAME_DELIM
                    + c.getAge() + INFO_DELIM
                    + c.getAddress() + INFO_DELIM
                    + c.getConditions();
            localModel.addElement(entry);
        }
    }


    @Override
    public void listSetUp() {
        localList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        localList.setSelectedIndex(0);
        localList.addListSelectionListener(this);
        localList.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(localList);
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
