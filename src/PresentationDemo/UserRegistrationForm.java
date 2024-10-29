package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public UserRegistrationForm() {
        setTitle("User Registration");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the layout manager
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create components
        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        JButton submitButton = new JButton("Register");
        statusLabel = new JLabel(" ");

        // Add components to the form
        add(new JLabel("Name:"));
        add(nameField);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        add(new JLabel("Email:"));
        add(emailField);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        add(new JLabel("Password:"));
        add(passwordField);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        add(submitButton);
        add(Box.createRigidArea(new Dimension(0, 10))); // Add space
        add(statusLabel);

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegistration();
            }
        });
    }

    private void handleRegistration() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("All fields are required!");
        } else {
            statusLabel.setForeground(Color.GREEN);
            statusLabel.setText("Registration successful!");
            // Here you could add code to handle registration logic (e.g., save to database)
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRegistrationForm form = new UserRegistrationForm();
            form.setVisible(true);
        });
    }
}
