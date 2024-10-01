package tut05.tank90battlefield;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tank90BattleField extends JFrame{
    private static final int CELL_SIZE = 50; // Size of each cell in the grid
    private JPanel battlefield;

    public Tank90BattleField() {
        setTitle("Tank 1990");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        battlefield = new JPanel();
        battlefield.setLayout(new GridLayout(10, 10)); // Example 10x10 grid
        add(battlefield, BorderLayout.CENTER);

        loadMap("battlefield.map"); // Load the map from a file
    }

    private void loadMap(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null && row < 10) { // Adjust based on grid size
                String[] elements = line.split(" ");
                for (int col = 0; col < elements.length; col++) {
                    battlefield.add(createCell(elements[col]));
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Component createCell(String element) {
        JPanel cell = new JPanel();
        switch (element) {
            case "W":
                cell.setBackground(Color.GRAY); // Wall
                break;
            case "T":
                cell.setBackground(Color.GREEN); // Tank
                break;
            case "P":
                cell.setBackground(Color.YELLOW); // Power-up
                break;
            default:
                cell.setBackground(Color.LIGHT_GRAY); // Empty space
                break;
        }
        cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        return cell;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tank90BattleField game = new Tank90BattleField();
            game.setVisible(true);
        });
    }
}
