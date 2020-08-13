package ui.listeners;

import ui.tabs.ListTab;

import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// implements behaviour of program when the remove button is clicked
// code referenced from Oracle's ListDemo project
public class AddListener implements ActionListener {
    private static final String ADD_CUSTOMER_SOUND = "./data/sounds/AddCustomerSound.wav";
    private static final String LOCAL_LIST_FILE = "./data/localList.txt";
    private static final String FOREIGN_LIST_FILE = "./data/foreignList.txt";

    private ListTab listTab;
    private String entry;

    public AddListener(ListTab listTab) {
        this.listTab = listTab;
    }

    //EFFECTS: adds user input to list of strings of customer data, then inserts the entry into either
    // after the selected index of the GUI list or at the front of the list if there is no selected index
    @Override
    public void actionPerformed(ActionEvent e) {
        entry = listTab.getCustomerEntry().getText();

        if (!entry.equals("")) {
            int index = listTab.getDistributionList().getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            playAddCustomerSound();
            listTab.getListModel().insertElementAt(entry, index);
            listTab.getCustomerEntry().requestFocusInWindow();
            listTab.getCustomerEntry().setText("");
            saveChangesToFile();
        }
    }

    //EFFECTS: if the user's input contains the sequence "BC", then save input to local file
    // otherwise, save input to foreign file
    public void saveChangesToFile() {
        try {
            if (entry.contains("BC")) {
                saveToLocalFile();
            } else {
                saveToForeignFile();
            }
        } catch (IOException ioException) {
            System.err.println("Caught IO Exception.");
            ioException.printStackTrace();
        }
    }

    //EFFECTS: saves user's input to local file
    // throw IOException if an exception is raised when opening or reading given file
    private void saveToLocalFile() throws IOException {
        java.io.FileWriter fileWriter = new java.io.FileWriter(new File(LOCAL_LIST_FILE));
        PrintWriter pw = new PrintWriter(fileWriter);
        for (int i = 0; i < listTab.getDistributionList().getModel().getSize(); i++) {
            String line = (String) listTab.getDistributionList().getModel().getElementAt(i);
            pw.println(line);
            pw.flush();
        }
        pw.close();
        fileWriter.close();
    }

    //EFFECTS: saves user's input to foreign file
    // throw IOException if an exception is raised when opening or reading given file
    private void saveToForeignFile() throws IOException {
        java.io.FileWriter fileWriter = new java.io.FileWriter(new File(FOREIGN_LIST_FILE));
        PrintWriter pw = new PrintWriter(fileWriter);
        for (int i = 0; i < listTab.getDistributionList().getModel().getSize(); i++) {
            String line = (String) listTab.getDistributionList().getModel().getElementAt(i);
            pw.println(line);
            pw.flush();
        }
        pw.close();
        fileWriter.close();
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

