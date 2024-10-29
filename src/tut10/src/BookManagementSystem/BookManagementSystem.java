package tut10.src.BookManagementSystem;

import javax.swing.*;
import java.awt.*;

public class BookManagementSystem extends JFrame {
    public JTextField titleField;
    public JTextField authorField;
    public JTextField priceField;
    public JButton addButton;
    public JButton viewButton;
    private JButton updateButton;
    private JButton deleteButton;
    public JTable bookTable;

    public BookManagementSystem() {
        setTitle("Book Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        addButton = new JButton("Add");
        viewButton = new JButton("View");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Table Panel
        bookTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(bookTable);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookManagementSystem frame = new BookManagementSystem();
            frame.setVisible(true);
        });
    }
}