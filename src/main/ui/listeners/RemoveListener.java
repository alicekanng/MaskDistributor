package ui.listeners;

import model.Customer;
import persistence.FileReader;
import ui.tabs.ListTab;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class RemoveListener implements ActionListener {
    private static final String REMOVE_CUSTOMER_SOUND = "./data/sounds/RemoveCustomerSound.wav";
    private ListTab listTab;
    private List<String> entries;
    private List<Customer> customers;

    private static final String NAME_DELIM = ": ";
    private static final String INFO_DELIM = " / ";

    private static final String LOCAL_LIST_FILE = "./data/localList.txt";
    private static final String FOREIGN_LIST_FILE = "./data/foreignList.txt";

    public RemoveListener(ListTab listTab) {
        this.listTab = listTab;
    }

    //EFFECTS: removes the entry at the selected index from the list
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = listTab.getDistributionList().getSelectedIndex();
        String entry = (String) listTab.getDistributionList().getSelectedValue();
        if (index != -1) {
            playRemoveCustomerSound();
            entries.add(entry);
            saveEntryToFile(entry);
            listTab.getListModel().remove(index);
            listTab.getDistributionList().setSelectedIndex(index);
            listTab.getDistributionList().ensureIndexIsVisible(index);
        }
    }

    public void saveEntryToFile(String entry) {
        FileReader fileReader = new FileReader();
        customers = fileReader.parseCustomers(entries);

        File originalFile = new File(FOREIGN_LIST_FILE);
        File tempFile = new File(FOREIGN_LIST_FILE + ".tmp");

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(originalFile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            Customer line = null;

            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(entry)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

        } catch (Exception e) {
            System.err.println("Caught Exception.");
        }

        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
        }

        if (!tempFile.renameTo(originalFile)) {
            System.out.println("Could not rename file.");
        }
//
//        for (Customer c : customers) {
//            if (c.getAddress().contains("BC")) {
//                saveToLocalFile(c);
//            } else {
//                saveToForeignFile(c);
//            }
//        }
    }

    public String customerToString(Customer c) {
        String entry = c.getName() + NAME_DELIM
                + c.getAddress() + INFO_DELIM
                + c.getAge() + INFO_DELIM
                + c.getConditions();
        return entry;
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
