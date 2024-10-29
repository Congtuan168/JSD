package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NoteTakingApp extends JFrame {
    private JTextArea noteArea;
    private JButton saveButton;
    private JButton loadButton;
    private JButton clearButton;
    private JFileChooser fileChooser;

    public NoteTakingApp() {
        setTitle("Note Taking Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create components
        noteArea = new JTextArea();
        saveButton = new JButton("Save Note");
        loadButton = new JButton("Load Note");
        clearButton = new JButton("Clear Note");
        fileChooser = new JFileChooser();

        // Add components to the frame
        add(new JScrollPane(noteArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNote();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadNote();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearNote();
            }
        });
    }

    private void saveNote() {
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(noteArea.getText());
                JOptionPane.showMessageDialog(this, "Note saved successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving note: " + e.getMessage());
            }
        }
    }

    private void loadNote() {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                noteArea.setText(""); // Clear current note
                String line;
                while ((line = reader.readLine()) != null) {
                    noteArea.append(line + "\n");
                }
                JOptionPane.showMessageDialog(this, "Note loaded successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading note: " + e.getMessage());
            }
        }
    }

    private void clearNote() {
        noteArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NoteTakingApp app = new NoteTakingApp();
            app.setVisible(true);
        });
    }
}
