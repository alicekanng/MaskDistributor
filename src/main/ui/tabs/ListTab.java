package ui.tabs;

import model.DistributionList;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public abstract class ListTab extends JPanel implements ListSelectionListener {
    private JPanel buttonPane;
    protected JButton addButton;
    protected JButton removeButton;
    private JTextField customerEntry;


    protected static final String NAME_DELIM = ": ";
    protected static final String INFO_DELIM = " / ";

    protected static final String ADD_STRING = "Add customer";
    protected static final String REMOVE_STRING = "Remove customer";

    public ListTab() {
        super(new BorderLayout());

        buttonPane = new JPanel();
        addButtonSetUp();
        removeButtonSetUp();
        customerEntrySetUp();
        buttonPanelSetUp();
    }

    //add elements loop through queue
    public abstract void addCustomersToListModel(DistributionList list);

    public abstract void listSetUp();

    public abstract void addButtonSetUp();

    public abstract void removeButtonSetUp();

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

    public void customerEntrySetUp() {
        customerEntry = new JTextField();
    }

    public JTextField getCustomerEntry() {
        return customerEntry;
    }

    public abstract DefaultListModel getListModel();

    public abstract JList getDistributionList();
}
