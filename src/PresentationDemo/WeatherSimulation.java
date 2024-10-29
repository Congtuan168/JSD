package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class Cloud {
    int x, y;

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += 1; // Move cloud to the right
        if (x > 800) x = -100; // Reset position if off-screen
    }

    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x, y, 100, 50);
        g.fillOval(x + 20, y - 20, 100, 50);
        g.fillOval(x + 50, y, 100, 50);
    }
}

class Raindrop {
    int x, y;

    public Raindrop(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void fall() {
        y += 5; // Move raindrop down
        if (y > 600) y = 0; // Reset position if off-screen
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 2, 10); // Draw raindrop
    }
}

public class WeatherSimulation extends JPanel implements ActionListener {
    private ArrayList<Cloud> clouds;
    private ArrayList<Raindrop> raindrops;
    private String weather = "SUNNY"; // Initial weather state

    public WeatherSimulation() {
        clouds = new ArrayList<>();
        raindrops = new ArrayList<>();
        Random random = new Random();

        // Create clouds
        for (int i = 0; i < 5; i++) {
            clouds.add(new Cloud(random.nextInt(800), random.nextInt(100)));
        }

        // Create raindrops
        for (int i = 0; i < 100; i++) {
            raindrops.add(new Raindrop(random.nextInt(800), random.nextInt(600)));
        }

        // Buttons to change weather
        JButton sunnyButton = new JButton("Sunny");
        sunnyButton.addActionListener(e -> weather = "SUNNY");
        JButton rainyButton = new JButton("Rainy");
        rainyButton.addActionListener(e -> weather = "RAINY");
        JButton stormyButton = new JButton("Stormy");
        stormyButton.addActionListener(e -> weather = "STORMY");

        // Layout for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sunnyButton);
        buttonPanel.add(rainyButton);
        buttonPanel.add(stormyButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);

        // Draw clouds
        for (Cloud cloud : clouds) {
            cloud.move();
            cloud.draw(g);
        }

        // Draw rain if weather is rainy
        if (weather.equals("RAINY")) {
            for (Raindrop drop : raindrops) {
                drop.fall();
                drop.draw(g);
            }
        }

        // Draw lightning if weather is stormy
        if (weather.equals("STORMY")) {
            drawLightning(g);
        }

        repaint(); // Keep updating the display
    }

    private void drawBackground(Graphics g) {
        if (weather.equals("SUNNY")) {
            g.setColor(Color.CYAN);
        } else if (weather.equals("RAINY")) {
            g.setColor(Color.GRAY);
        } else if (weather.equals("STORMY")) {
            g.setColor(Color.DARK_GRAY);
        }
        g.fillRect(0, 0, getWidth(), getHeight()); // Fill background
    }

    private void drawLightning(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine(400, 50, 420, 80);
        g.drawLine(420, 80, 380, 100);
        g.drawLine(380, 100, 410, 130);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Weather Simulation");
        WeatherSimulation simulation = new WeatherSimulation();
        frame.add(simulation);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}