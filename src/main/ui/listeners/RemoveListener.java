package ui.listeners;

import ui.tabs.ListTab;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RemoveListener implements ActionListener {
    private static final String REMOVE_CUSTOMER_SOUND = "./data/RemoveCustomerSound.wav";
    private ListTab listUI;

    public RemoveListener(ListTab listUI) {
        this.listUI = listUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = listUI.getDistributionList().getSelectedIndex();
        if (index != -1) {
            playSound("RemoveCustomerSound.wav");
            listUI.getListModel().remove(index);
            listUI.getDistributionList().setSelectedIndex(index);
            listUI.getDistributionList().ensureIndexIsVisible(index);
        }
    }

    public void playSound(String sound) {
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
