package ui.listeners;

import ui.DistributionListUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ForeignButtonListener implements ActionListener {
    private static final String LOAD_LIST_SOUND = "./data/LoadListSound.wav";

    private JButton foreignButton;
    private DistributionListUI ui;

    public ForeignButtonListener(JButton foreignButton, DistributionListUI ui) {
        this.foreignButton = foreignButton;
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        if (buttonPressed.equals(foreignButton.getText())) {
            playLoadListSound();
            ui.getSidebar().setSelectedIndex(DistributionListUI.FOREIGN_TAB_INDEX);
        }
    }

    //code referenced from: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
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
