package ui.listeners;

import model.Customer;
import persistence.FileReader;
import persistence.FileWriter;
import ui.tabs.ListTab;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

public class AddListener implements ActionListener {
    private static final String ADD_CUSTOMER_SOUND = "./data/AddCustomerSound.wav";

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

    private void saveToForeignFile(Customer c) {
        try {
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(FOREIGN_LIST_FILE), true));
            fileWriter.write(c);
            fileWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Cannot find file to save to!");
        }
    }

    private void saveToLocalFile(Customer c) {
        try {
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(LOCAL_LIST_FILE), true));
            fileWriter.write(c);
            fileWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Cannot find file to save to!");
        }
    }

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

