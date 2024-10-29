package tut09;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BMIServer extends JFrame {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private JLabel resultLabel;

    public BMIServer() {
        setTitle("BMI Server");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel weightLabel = new JLabel("Weight (pounds):");
        JLabel heightLabel = new JLabel("Height (inches):");
        resultLabel = new JLabel("BMI Result: ");

        panel.add(weightLabel);
        panel.add(new JLabel(""));
        panel.add(heightLabel);
        panel.add(new JLabel(""));
        panel.add(new JPanel());
        panel.add(resultLabel);
        getContentPane().add(panel, BorderLayout.CENTER);

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("BMI Server started. Waiting for connections...");

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String[] data = in.readLine().split(" ");
                double weight = Double.parseDouble(data[0]);
                double height = Double.parseDouble(data[1]);
                double bmi = calculateBMI(weight, height);

                String resultMessage = "BMI is: " + bmi;
                out.println(resultMessage);
                resultLabel.setText(resultMessage); // Update server label
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double calculateBMI(double weight, double height) {
        return weight / ((height / 39.37) * (height / 39.37)); // Convert inches to meters
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMIServer().setVisible(true));
    }
}