package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingSquare extends JPanel implements ActionListener {
    private double angle = 0; // Rotation angle

    public RotatingSquare() {
        Timer timer = new Timer(30, this);
        timer.start(); // Start the animation timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Save the current transform
        g2d.translate(getWidth() / 2, getHeight() / 2); // Move to center
        g2d.rotate(angle); // Rotate around center
        g2d.setColor(Color.BLUE);
        g2d.fillRect(-25, -25, 50, 50); // Draw square
        g2d.setColor(Color.BLACK);
        g2d.drawRect(-25, -25, 50, 50); // Draw border

        angle += Math.toRadians(5); // Increment angle
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Request to repaint
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Square Animation");
        RotatingSquare square = new RotatingSquare();
        frame.add(square);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
