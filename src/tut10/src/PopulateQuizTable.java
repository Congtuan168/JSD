package tut10.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PopulateQuizTable {
    private static final String urls = "https://github.com/Congtuan168/JSD/blob/main/src/quiz.txt";
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3307/jsd"; // Update with your database URL
        String username = "root"; // Update with your database username
        String password = "12345678"; // Update with your database password

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            conn.setAutoCommit(false);
            String insertSQL = "INSERT INTO Quiz (questionId, question, choicea, choiceb, choicec, choiced, answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            URL url = new URL(urls);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                int questionId = 1;

                while ((line = reader.readLine()) != null) {
                    // Read question
                    String question = line.trim();

                    // Read choices
                    String choiceA = reader.readLine().substring(3).trim(); // Remove "a. "
                    String choiceB = reader.readLine().substring(3).trim(); // Remove "b. "
                    String choiceC = reader.readLine().substring(3).trim(); // Remove "c. "
                    String choiceD = reader.readLine().substring(3).trim(); // Remove "d. "

                    // Read answer
                    String answerLine = reader.readLine();
                    String answer = answerLine.substring(answerLine.indexOf(":") + 1).trim(); // Extract answer

                    // Set parameters and add to batch
                    pstmt.setInt(1, questionId++);
                    pstmt.setString(2, question);
                    pstmt.setString(3, choiceA);
                    pstmt.setString(4, choiceB);
                    pstmt.setString(5, choiceC);
                    pstmt.setString(6, choiceD);
                    pstmt.setString(7, answer);
                    pstmt.addBatch();
                }
            }

            pstmt.executeBatch();
            conn.commit();
            System.out.println("Quiz data imported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
