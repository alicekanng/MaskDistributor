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
    private ListTab listTab;

    public RemoveListener(ListTab listTab) {
        this.listTab = listTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = listTab.getDistributionList().getSelectedIndex();
        if (index != -1) {
            playRemoveCustomerSound();
            listTab.getListModel().remove(index);
            listTab.getDistributionList().setSelectedIndex(index);
            listTab.getDistributionList().ensureIndexIsVisible(index);
        }
    }

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
