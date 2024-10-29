package tut09;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class BMIClient extends JFrame {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public BMIClient() {
        setTitle("BMI Client");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel weightLabel = new JLabel("Weight (pounds):");
        JTextField weightField = new JTextField();
        JLabel heightLabel = new JLabel("Height (inches):");
        JTextField heightField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel();

        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(new JPanel());
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            try {
                socket = new Socket("localhost", 12345);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println(weightField.getText() + " " + heightField.getText());
                String bmiResult = in.readLine();
                resultLabel.setText(bmiResult);

                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(resultLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BMIClient().setVisible(true);
            }
        });
    }
}