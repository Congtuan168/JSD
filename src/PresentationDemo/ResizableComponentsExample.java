package PresentationDemo;

import javax.swing.*;
import java.awt.*;

public class ResizableComponentsExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Resizable Components Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create buttons with size constraints
        JButton button1 = new JButton("Button 1");
        button1.setPreferredSize(new Dimension(150, 50));
        button1.setMinimumSize(new Dimension(100, 30));
        button1.setMaximumSize(new Dimension(200, 75));

        JButton button2 = new JButton("Button 2");
        button2.setPreferredSize(new Dimension(150, 50));
        button2.setMinimumSize(new Dimension(100, 30));
        button2.setMaximumSize(new Dimension(200, 75));

        // Add buttons to the frame
        frame.add(button1);
        frame.add(button2);

        frame.setSize(400, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }
}
