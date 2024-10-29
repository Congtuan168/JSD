package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader extends JFrame {
    private JTextField urlField;
    private JProgressBar progressBar;
    private JButton downloadButton;
    private JTextArea outputArea;

    public FileDownloader() {
        setTitle("File Downloader");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout manager
        setLayout(new BorderLayout());

        // Create components
        urlField = new JTextField();
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        downloadButton = new JButton("Download");
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Add components to the frame
        add(new JLabel("Enter File URL:"), BorderLayout.NORTH);
        add(urlField, BorderLayout.CENTER);
        add(downloadButton, BorderLayout.EAST);
        add(progressBar, BorderLayout.SOUTH);
        add(new JScrollPane(outputArea), BorderLayout.WEST);

        // Action listener for the download button
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDownload();
            }
        });
    }

    private void startDownload() {
        String fileUrl = urlField.getText();
        downloadButton.setEnabled(false); // Disable button to prevent multiple clicks
        outputArea.setText("Starting download...\n");

        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    URL url = new URL(fileUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    int fileLength = connection.getContentLength();
                    try (InputStream input = new BufferedInputStream(connection.getInputStream());
                         FileOutputStream output = new FileOutputStream("downloaded_file")) {

                        byte[] data = new byte[1024];
                        long total = 0;
                        int count;

                        while ((count = input.read(data)) != -1) {
                            total += count;
                            if (fileLength > 0) {
                                publish((int) (total * 100 / fileLength)); // Calculate progress
                            }
                            output.write(data, 0, count); // Write to file
                        }
                    }
                } catch (IOException ex) {
                    outputArea.append("Error: " + ex.getMessage() + "\n");
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (int value : chunks) {
                    progressBar.setValue(value); // Update progress bar
                    outputArea.append("Downloaded " + value + "%\n"); // Update output area
                }
            }

            @Override
            protected void done() {
                outputArea.append("Download complete!\n");
                downloadButton.setEnabled(true); // Re-enable the button
            }
        };

        worker.execute(); // Start the background task
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileDownloader downloader = new FileDownloader();
            downloader.setVisible(true);
        });
    }
}
