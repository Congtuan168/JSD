package tut10.src.BookManagementSystem;

import java.sql.*;

public class BookDAO {
    private Connection connect() {
        // SQLite connection string
        String jdbc = "jdbc:mysql://localhost:3307/jsd"; //I forget password for the 3306 host
        String username = "root";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(jdbc, username, password)) {
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insertBook(String title, String author, double price) {
        String sql = "INSERT INTO Books(title, author, price) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setDouble(3, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getAllBooks() {
        String sql = "SELECT * FROM Books";
        ResultSet rs = null;
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    // Implement updateBook and deleteBook methods similarly
}
