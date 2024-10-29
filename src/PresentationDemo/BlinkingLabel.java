package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlinkingLabel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Blinking Label Example");
        JLabel label = new JLabel("Blinking Text", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        frame.add(label);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame

        // Timer for blinking effect
        Timer timer = new Timer(500, new ActionListener() {
            boolean visible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(visible);
                visible = !visible; // Toggle visibility
            }
        });
        timer.start(); // Start the timer
    }
}
