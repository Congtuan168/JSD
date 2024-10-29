package PresentationDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleMediaPlayer extends JFrame {
    private JLabel mediaTitle;
    private JSlider volumeSlider;

    public SimpleMediaPlayer() {
        setTitle("Simple Media Player");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the layout manager
        setLayout(new BorderLayout());

        // Create the media title display area
        mediaTitle = new JLabel("No Media Playing", SwingConstants.CENTER);
        mediaTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(mediaTitle, BorderLayout.CENTER);

        // Create control buttons
        JPanel controlPanel = new JPanel();
        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");

        playButton.addActionListener(new ButtonClickListener());
        pauseButton.addActionListener(new ButtonClickListener());
        stopButton.addActionListener(new ButtonClickListener());

        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Create volume control
        JPanel volumePanel = new JPanel();
        volumePanel.setLayout(new BorderLayout());

        JLabel volumeLabel = new JLabel("Volume:");
        volumeSlider = new JSlider(0, 100, 50);
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        volumePanel.add(volumeLabel, BorderLayout.NORTH);
        volumePanel.add(volumeSlider, BorderLayout.CENTER);

        add(volumePanel, BorderLayout.EAST);

        // Create playlist area
        JPanel playlistPanel = new JPanel();
        playlistPanel.setLayout(new BoxLayout(playlistPanel, BoxLayout.Y_AXIS));
        playlistPanel.add(new JLabel("Playlist"));
        playlistPanel.add(new JList<>(new String[]{"Song 1", "Song 2", "Song 3"}));
        add(playlistPanel, BorderLayout.WEST);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Play":
                    mediaTitle.setText("Playing Media...");
                    break;
                case "Pause":
                    mediaTitle.setText("Media Paused");
                    break;
                case "Stop":
                    mediaTitle.setText("No Media Playing");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleMediaPlayer player = new SimpleMediaPlayer();
            player.setVisible(true);
        });
    }
}