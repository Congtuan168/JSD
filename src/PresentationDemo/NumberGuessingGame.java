package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private JTextField guessField;
    private JLabel feedbackLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout manager
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create components
        guessField = new JTextField();
        JButton guessButton = new JButton("Guess");
        JButton restartButton = new JButton("Restart");
        feedbackLabel = new JLabel("Enter a number between 1 and 100");

        // Add components to the frame
        add(feedbackLabel);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        add(guessField);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        add(guessButton);
        add(restartButton);

        // Action listeners
        guessButton.addActionListener(new GuessButtonListener());
        restartButton.addActionListener(new RestartButtonListener());

        // Start a new game
        startNewGame();
    }

    private void startNewGame() {
        randomNumber = new Random().nextInt(100) + 1; // Random number between 1 and 100
        feedbackLabel.setText("Enter a number between 1 and 100");
        guessField.setText("");
        guessField.setEnabled(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                if (guess < 1 || guess > 100) {
                    feedbackLabel.setText("Please guess a number between 1 and 100.");
                } else if (guess < randomNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (guess > randomNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Correct! The number was " + randomNumber);
                    guessField.setEnabled(false); // Disable input after correct guess
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Invalid input! Please enter a number.");
            }
        }
    }

    private class RestartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame(); // Restart the game
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}
