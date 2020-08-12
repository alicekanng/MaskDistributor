package ui.listeners;

import model.Customer;
import persistence.FileReader;
import persistence.FileWriter;
import ui.tabs.ListTab;

import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

public class AddListener implements ActionListener {
    private static final String ADD_CUSTOMER_SOUND = "./data/sounds/AddCustomerSound.wav";

    private ListTab listTab;
    private List<String> entries;
    private List<Customer> customers;

    private static final String LOCAL_LIST_FILE = "./data/localList.txt";
    private static final String FOREIGN_LIST_FILE = "./data/foreignList.txt";

    public AddListener(ListTab listTab) {
        this.listTab = listTab;
        entries = new LinkedList<>();
        customers = new LinkedList<>();
    }

    //EFFECTS: adds user input to list of strings of customer data, then inserts the entry into either
    // after the selected index of the GUI list or at the front of the list if there is no selected index
    @Override
    public void actionPerformed(ActionEvent e) {
        String entry = listTab.getCustomerEntry().getText();
        entries.add(entry);

        if (!entry.equals("")) {
            int index = listTab.getDistributionList().getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            playAddCustomerSound();
            listTab.getListModel().insertElementAt(entry, index);
            saveEntryToFile();
            listTab.getCustomerEntry().requestFocusInWindow();
            listTab.getCustomerEntry().setText("");
        }
    }

    //EFFECTS: parses the list of strings of customer data into a list of customers, then saves the list into a
    // file according to the customer's address
    public void saveEntryToFile() {
        FileReader fileReader = new FileReader();
        customers = fileReader.parseCustomers(entries);

        for (Customer c : customers) {
            if (c.getAddress().contains("BC")) {
                saveToLocalFile(c);
            } else {
                saveToForeignFile(c);
            }
        }
    }

    //EFFECTS: helper to save the customers into the Foreign list file
    private void saveToForeignFile(Customer c) {
        try {
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(FOREIGN_LIST_FILE), true));
            fileWriter.write(c);
            fileWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Cannot find file to save to!");
        }
    }

    //EFFECTS: helper to save the customers into the Local list file
    private void saveToLocalFile(Customer c) {
        try {
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(LOCAL_LIST_FILE), true));
            fileWriter.write(c);
            fileWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Cannot find file to save to!");
        }
    }

    //EFFECTS: plays sound every time the add button is pressed
    //code referenced from: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public void playAddCustomerSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ADD_CUSTOMER_SOUND));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Caught exception when trying to play sound.");
        }
    }
}

