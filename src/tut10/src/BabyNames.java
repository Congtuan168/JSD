package tut10.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BabyNames {
    private static final String[] urls = {
        "https://github.com/Congtuan168/JSD/blob/main/src/babyname1.txt"
    };
    public static void main(String[] args) {
        String jdbc = "jdbc:mysql://localhost:3307/jsd"; //I forget password for the 3306 host
        String username = "root";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(jdbc, username, password)) {
            connection.setAutoCommit(false);
            String insertSQL = "INSERT INTO babyname (year1, name1, gender1, count1) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            for (String urlString : urls) {
                URL url = new URL(urlString);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] tokens = line.split("\t");
                        if (tokens.length == 4) {
                            int year = Integer.parseInt(tokens[0]);
                            String name = tokens[1];
                            String gender = tokens[2];
                            int count = Integer.parseInt(tokens[3]);

                            preparedStatement.setInt(1, year);
                            System.out.println(year);
                            preparedStatement.setString(2, name);
                            System.out.println(name);
                            preparedStatement.setString(3, gender);
                            System.out.println(gender);
                            preparedStatement.setInt(4, count);
                            System.out.println(count);
                            preparedStatement.addBatch();
                        }
                    }
                }
            }
            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Data imported successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
