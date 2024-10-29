package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter extends JFrame {
    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextArea resultDisplay;
    private JButton convertButton;

    // Replace with your ExchangeRate-API API key
    private static final String API_KEY = "31d9f989181da53d6a536ca1";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s";

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create components
        amountField = new JTextField();
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "JPY", "AUD"});
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "JPY", "AUD"});
        resultDisplay = new JTextArea();
        resultDisplay.setEditable(false);
        convertButton = new JButton("Convert");

        // Add components to the frame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("From Currency:"));
        inputPanel.add(fromCurrency);
        inputPanel.add(new JLabel("To Currency:"));
        inputPanel.add(toCurrency);

        add(inputPanel, BorderLayout.NORTH);
        add(convertButton, BorderLayout.CENTER);
        add(new JScrollPane(resultDisplay), BorderLayout.SOUTH);

        // Action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String amountText = amountField.getText();
        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();

        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a number.");
            return;
        }

        convertButton.setEnabled(false); // Disable button during conversion
        resultDisplay.setText("Fetching conversion rate...\n");

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    String urlString = String.format(API_URL, API_KEY, from, to);
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    int responseCode = conn.getResponseCode();
                    if (responseCode != HttpURLConnection.HTTP_OK) {
                        publish("Error fetching conversion rate: Server returned HTTP " + responseCode);
                        return null;
                    }

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Log the raw response for debugging
                    String jsonResponse = response.toString();
                    System.out.println("API Response: " + jsonResponse);

                    // Process the JSON response
                    double conversionRate = parseConversionRate(jsonResponse);
                    double convertedAmount = amount * conversionRate;

                    publish(String.format("%.2f %s = %.2f %s", amount, from, convertedAmount, to));
                } catch (Exception e) {
                    publish("Error fetching conversion rate: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String message : chunks) {
                    resultDisplay.setText(message); // Update result display
                }
            }

            @Override
            protected void done() {
                convertButton.setEnabled(true); // Re-enable the button
            }
        };

        worker.execute(); // Start the background task
    }

    private double parseConversionRate(String jsonResponse) {
        // Check if the response contains an expected structure
        if (jsonResponse.contains("conversion_rate")) {
            String[] parts = jsonResponse.split(":");
            String rateString = parts[1].replaceAll("[^0-9.]", "").trim(); // Extract numerical value
            return Double.parseDouble(rateString);
        } else {
            System.out.println("Error: Unexpected response format.");
            return 0; // Return 0 to indicate failure
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
