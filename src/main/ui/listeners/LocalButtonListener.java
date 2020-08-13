package ui.listeners;

import ui.DistributionListUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// implements behaviour of program when the local list button is clicked
public class LocalButtonListener implements ActionListener {
    private static final String LOAD_LIST_SOUND = "./data/sounds/LoadListSound.wav";

    private JButton localButton;
    private DistributionListUI ui;

    public LocalButtonListener(JButton localButton, DistributionListUI ui) {
        this.localButton = localButton;
        this.ui = ui;
    }

    //EFFECTS: switches the screen to the local list tab once the load local list button is pressed from the home tab
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        if (buttonPressed.equals(localButton.getText())) {
            playLoadListSound();
            ui.getSidebar().setSelectedIndex(DistributionListUI.LOCAL_TAB_INDEX);
        }
    }

    //EFFECTS: plays sound every time the local list button is pressed
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
