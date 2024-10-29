package PresentationDemo;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SoundEffectExample {

    // Method to play sound
    public static void playSound(String soundFile) {
        try {
            File sound = new File(soundFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Sound Effect Example");
        JButton button = new JButton("Click Me");

        // Action listener for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("src/sounds/mouse-clicks-6849.wav"); // Path to the sound file
            }
        });

        // Set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }
}
