package ui.listeners;

import ui.tabs.ListTab;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddListener implements ActionListener {
    private static final String ADD_CUSTOMER_SOUND = "./data/AddCustomerSound.wav";

    private JButton addButton;
    private ListTab listTab;
    private boolean alreadyEnabled;

    public AddListener(JButton addButton, ListTab listTab) {
        this.addButton = addButton;
        this.listTab = listTab;
        alreadyEnabled = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String entry = listTab.getCustomerEntry().getText();
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
        }
    }

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

