package tut10.src.BookManagementSystem;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookController {
    private BookManagementSystem view;
    private BookDAO model;

    public BookController(BookManagementSystem view, BookDAO model) {
        this.view = view;
        this.model = model;

        this.view.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = view.titleField.getText();
                String author = view.authorField.getText();
                double price = Double.parseDouble(view.priceField.getText());
                model.insertBook(title, author, price);
                updateTable();
            }
        });

        this.view.viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });

        // Add action listeners for updateButton and deleteButton
    }

    private void updateTable() {
        ResultSet rs = model.getAllBooks();
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Price"}, 0);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                tableModel.addRow(new Object[]{id, title, author, price});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.bookTable.setModel(tableModel);
    }
}
