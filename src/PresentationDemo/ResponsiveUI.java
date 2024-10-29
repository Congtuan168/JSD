package PresentationDemo;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResponsiveUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Responsive Design Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JLabel label = new JLabel("Resize the window!", SwingConstants.CENTER);
        frame.add(label);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Adjust label size based on frame size
                int width = frame.getWidth();
                label.setFont(label.getFont().deriveFont((float) width / 20)); // Example adjustment
            }
        });

        frame.setVisible(true);
    }
}
