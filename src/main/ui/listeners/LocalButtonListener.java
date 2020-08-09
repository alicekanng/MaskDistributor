package ui.listeners;

import ui.DistributionListUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LocalButtonListener implements ActionListener {
    private static final String LOAD_LIST_SOUND = "./data/LoadListSound.wav";

    private JButton localButton;
    private DistributionListUI ui;

    public LocalButtonListener(JButton localButton, DistributionListUI ui) {
        this.localButton = localButton;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        if (buttonPressed.equals(localButton.getText())) {
            playLoadListSound();
            ui.getSidebar().setSelectedIndex(DistributionListUI.LOCAL_TAB_INDEX);
        }
    }

    public void playLoadListSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(LOAD_LIST_SOUND));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Caught exception when trying to play sound.");
        }
    }
}
