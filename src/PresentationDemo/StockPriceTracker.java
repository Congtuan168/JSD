package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockPriceTracker extends JFrame {
    private JTextField stockField;
    private JTextArea priceDisplay;
    private JButton fetchButton;
    private StockPriceAnimation animationPanel; // Animation panel

    // Replace with a public stock price API URL
    private static final String API_URL = "https://api.twelvedata.com/v1/price?symbol=%s&apikey=demo"; // Demo API key

    public StockPriceTracker() {
        setTitle("Stock Price Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create components
        stockField = new JTextField();
        fetchButton = new JButton("Fetch Price");
        priceDisplay = new JTextArea();
        priceDisplay.setEditable(false);

        // Create the animation panel
        animationPanel = new StockPriceAnimation();

        // Add components to the frame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(stockField, BorderLayout.CENTER);
        inputPanel.add(fetchButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(priceDisplay), BorderLayout.CENTER);
        add(animationPanel, BorderLayout.SOUTH); // Add animation panel

        // Action listener for the fetch button
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchStockPrice();
            }
        });
    }

    private void fetchStockPrice() {
        String stockSymbol = stockField.getText().toUpperCase();
        if (stockSymbol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a stock symbol.");
            return;
        }

        fetchButton.setEnabled(false); // Disable button during fetch
        priceDisplay.setText("Fetching stock price...\n");
        animationPanel.resetAnimation(); // Reset any ongoing animation

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    String urlString = String.format(API_URL, stockSymbol);
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Process the JSON response manually
                    publish(response.toString()); // Send the response to process method
                } catch (Exception e) {
                    publish("Error fetching stock price: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String message : chunks) {
                    priceDisplay.setText(message); // Update price display
                    updateAnimation(message); // Update animation based on price
                }
            }

            @Override
            protected void done() {
                fetchButton.setEnabled(true); // Re-enable the button
            }
        };

        worker.execute(); // Start the background task
    }

    private void updateAnimation(String jsonResponse) {
        try {
            String priceString = extractValue(jsonResponse, "price");
            String previousPriceString = extractValue(jsonResponse, "previous_close"); // Assuming the API returns previous close

            if (priceString != null && previousPriceString != null) {
                double price = Double.parseDouble(priceString);
                double previousPrice = Double.parseDouble(previousPriceString);

                if (price > previousPrice) {
                    animationPanel.setPriceCondition(StockPriceAnimation.Condition.INCREASING);
                } else if (price < previousPrice) {
                    animationPanel.setPriceCondition(StockPriceAnimation.Condition.DECREASING);
                } else {
                    animationPanel.setPriceCondition(StockPriceAnimation.Condition.STABLE);
                }

                priceDisplay.append(String.format("\nCurrent Price: $%.2f", price));
            }
        } catch (Exception e) {
            priceDisplay.append("\nError processing stock data: " + e.getMessage());
        }
    }

    private String extractValue(String jsonResponse, String key) {
        String searchKey = "\"" + key + "\":";
        int startIndex = jsonResponse.indexOf(searchKey);
        if (startIndex == -1) {
            return null; // Key not found
        }
        startIndex += searchKey.length();
        int endIndex = jsonResponse.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonResponse.indexOf("}", startIndex);
        }
        return jsonResponse.substring(startIndex, endIndex).replace("\"", "").trim(); // Return the value without quotes
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StockPriceTracker tracker = new StockPriceTracker();
            tracker.setVisible(true);
        });
    }

    // Inner class for stock price animation
    class StockPriceAnimation extends JPanel {
        public enum Condition { INCREASING, DECREASING, STABLE }

        private Condition currentCondition;

        public StockPriceAnimation() {
            currentCondition = Condition.STABLE; // Default to stable
            setPreferredSize(new Dimension(400, 100));
        }

        public void setPriceCondition(Condition condition) {
            this.currentCondition = condition;
            repaint(); // Redraw the panel
        }

        public void resetAnimation() {
            currentCondition = Condition.STABLE; // Reset to default
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            switch (currentCondition) {
                case INCREASING:
                    g.setColor(Color.GREEN);
                    g.fillRect(0, 0, getWidth(), getHeight()); // Green for increasing
                    g.drawString("Price Increasing!", 150, 50);
                    break;
                case DECREASING:
                    g.setColor(Color.RED);
                    g.fillRect(0, 0, getWidth(), getHeight()); // Red for decreasing
                    g.drawString("Price Decreasing!", 150, 50);
                    break;
                case STABLE:
                default:
                    g.setColor(Color.YELLOW);
                    g.fillRect(0, 0, getWidth(), getHeight()); // Yellow for stable
                    g.drawString("Price Stable!", 150, 50);
                    break;
            }
        }
    }
}