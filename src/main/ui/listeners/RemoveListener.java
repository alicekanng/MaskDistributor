package ui.listeners;

import ui.tabs.ListTab;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// implements behaviour of program when the remove button is clicked
// code referenced from Oracle's ListDemo project
public class RemoveListener implements ActionListener {
    private static final String REMOVE_CUSTOMER_SOUND = "./data/sounds/RemoveCustomerSound.wav";
    private ListTab listTab;
    private String selected;

    private static final String LOCAL_LIST_FILE = "./data/localList.txt";
    private static final String FOREIGN_LIST_FILE = "./data/foreignList.txt";

    public RemoveListener(ListTab listTab) {
        this.listTab = listTab;
    }

    //EFFECTS: removes the entry at the selected index from the list
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = listTab.getDistributionList().getSelectedIndex();
        selected = (String) listTab.getDistributionList().getModel().getElementAt(index);

        if (index != -1) {
            playRemoveCustomerSound();
            listTab.getListModel().remove(index);
            listTab.getDistributionList().setSelectedIndex(index);
            listTab.getDistributionList().ensureIndexIsVisible(index);
            saveChangesToFile();
        }
    }

    //EFFECTS: if the user's selected entry contains "BC", save deletion to local list file
    // otherwise, save deletion to foreign list file
    public void saveChangesToFile() {
        try {
            if (selected.contains("BC")) {
                saveToLocalFile();
            } else {
                saveToForeignFile();
            }
        } catch (IOException ioException) {
            System.err.println("Caught IO Exception.");
            ioException.printStackTrace();
        }
    }

    //EFFECTS: saves user's deletion to local file
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

    //EFFECTS: saves user's deletion to foreign file
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

    //EFFECTS: plays sound every time the remove button is pressed
    //code referenced from: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public void playRemoveCustomerSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(REMOVE_CUSTOMER_SOUND));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Caught exception when trying to play sound.");
        }
    }
}
