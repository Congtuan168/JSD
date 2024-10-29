package PresentationDemo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBallGame extends JPanel {
    private int x = 168;               // Ball's x position
    private int y = 230;               // Ball's y position
    private int xVelocity = 20;         // Ball's horizontal speed
    private int yVelocity = 20;         // Ball's vertical speed
    private Timer timer;

    public BouncingBallGame() {
        // Timer to update the game state
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint(); // Repaint the panel
            }
        });
        timer.start(); // Start the timer
    }

    private void updateGame() {
        // Update ball position
        x += xVelocity;
        y += yVelocity;

        // Bounce off the walls
        if (x < 0 || x > getWidth() - 30) {
            xVelocity = -xVelocity; // Reverse direction
        }
        if (y < 0 || y > getHeight() - 30) {
            yVelocity = -yVelocity; // Reverse direction
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30); // Draw the ball
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Bouncing Ball Game");
        BouncingBallGame game = new BouncingBallGame();
        frame.add(game);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame
    }
}