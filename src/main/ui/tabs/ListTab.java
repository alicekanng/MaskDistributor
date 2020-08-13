package ui.tabs;

import model.DistributionList;
import ui.listeners.AddListener;
import ui.listeners.RemoveListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// Constructs the general structure of the list tabs,
// setting up the scroll pane, buttons, and other common functionalities
public abstract class ListTab extends JPanel implements ListSelectionListener {
    protected static final String NAME_DELIM = ": ";
    protected static final String INFO_DELIM = " / ";

    protected static final String ADD_STRING = "Add customer";
    protected static final String REMOVE_STRING = "Remove customer";

    private JPanel buttonPane;
    protected JButton addButton;
    protected JButton removeButton;
    private JTextField customerEntry;
    private AddListener addListener;
    private RemoveListener removeListener;

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

    //MODIFIES: this
    //EFFECTS: adds all the customers from given list into the GUI list
    public abstract void addCustomersToListModel(DistributionList list);

    //MODIFIES: this
    //EFFECTS: sets up the GUI list
    public abstract void listSetUp();

    //MODIFIES: this
    //EFFECTS: constructs and sets up add customer button
    public void addButtonSetUp() {
        addButton = new JButton(ADD_STRING);
        addButton.setActionCommand(ADD_STRING);
        addButton.addActionListener(addListener);
    }

    //MODIFIES: this
    //EFFECTS: constructs and sets up remove customer button
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

    //getters:
    public JTextField getCustomerEntry() {
        return customerEntry;
    }

    public abstract DefaultListModel getListModel();

    public abstract JList getDistributionList();
}
