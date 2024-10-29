package PresentationDemo;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ClickMeExample {

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
        JFrame frame = new JFrame("Click Me");
        frame.setLayout(new BorderLayout());

        // Create a button with a tooltip
        JButton button = new JButton("Click Me");
        button.setToolTipText("Click to perform an action and hear a sound!");

        // Create a loading indicator
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setString("Processing...");
        progressBar.setStringPainted(true);
        progressBar.setVisible(false); // Initially hidden

        // Action listener for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show loading indicator
                progressBar.setVisible(true);
                // Play sound effect
                playSound("src/sounds/nhac-nen-game.ogg"); // Adjust the path to your sound file

                // Simulate a task
                SwingUtilities.invokeLater(() -> {
                    try {
                        // Simulating a delay (e.g., processing time)
                        Thread.sleep(2000); // 2 seconds
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        // Hide loading indicator and show feedback
                        progressBar.setVisible(false);
                        JOptionPane.showMessageDialog(frame, "Action completed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        });

        // Add components to the frame
        frame.add(button, BorderLayout.CENTER);
        frame.add(progressBar, BorderLayout.SOUTH);

        // Frame settings
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
