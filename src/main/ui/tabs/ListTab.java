package ui.tabs;

import model.DistributionList;
import ui.Application;
import ui.listeners.AddListener;
import ui.listeners.RemoveListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public abstract class ListTab extends JPanel implements ListSelectionListener {
    private JPanel buttonPane;
    protected JButton addButton;
    protected JButton removeButton;
    private JTextField customerEntry;
    private AddListener addListener;
    private RemoveListener removeListener;

    protected static final String NAME_DELIM = ": ";
    protected static final String INFO_DELIM = " / ";

    protected static final String ADD_STRING = "Add customer";
    protected static final String REMOVE_STRING = "Remove customer";

    public ListTab() {
        super(new BorderLayout());

        buttonPane = new JPanel();
        addListener = new AddListener(this);
        removeListener = new RemoveListener(this);

        addButtonSetUp();
        removeButtonSetUp();
        customerEntrySetUp();
        buttonPanelSetUp();
    }

    public abstract void addCustomersToListModel(DistributionList list);

    public abstract void listSetUp();

    public void addButtonSetUp() {
        addButton = new JButton(ADD_STRING);
        addButton.setActionCommand(ADD_STRING);
        addButton.addActionListener(addListener);
    }

    public void removeButtonSetUp() {
        removeButton = new JButton(REMOVE_STRING);
        removeButton.setActionCommand(REMOVE_STRING);
        removeButton.addActionListener(removeListener);
    }

    //MODIFIES: this
    //EFFECTS: sets up the button and textbox panel on both of the list tabs
    public void buttonPanelSetUp() {
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(customerEntry);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(removeButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.add(buttonPane, BorderLayout.PAGE_END);
    }

    //MODIFIES: this
    //EFFECTS: constructs new customer entry textbox
    public void customerEntrySetUp() {
        customerEntry = new JTextField();
    }

    public JTextField getCustomerEntry() {
        return customerEntry;
    }

    public abstract DefaultListModel getListModel();

    public abstract JList getDistributionList();
}
