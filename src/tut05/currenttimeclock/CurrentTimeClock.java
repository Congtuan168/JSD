package tut05.currenttimeclock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimeClock extends JFrame {
    public JLabel jLabel = new JLabel();

    public CurrentTimeClock() {
        setTitle("Current Clock");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        jLabel.setFont(new Font("Arial", Font.BOLD, 24));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(jLabel, BorderLayout.CENTER);
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
        updateTime(); // Initialize with the current time
    }

    private void updateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        jLabel.setText(currentDateTime.format(formatter));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrentTimeClock clock = new CurrentTimeClock();
            clock.setVisible(true);
        });
    }
}



