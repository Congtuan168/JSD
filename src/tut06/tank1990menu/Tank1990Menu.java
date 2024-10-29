package tut06.tank1990menu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Tank1990Menu extends JFrame {
    private Image mainBackgroundImage;
    private Image battleCityText;
    public Tank1990Menu() throws IOException {
        //create the main background by the brick image
        mainBackgroundImage = Toolkit.getDefaultToolkit().getImage("src/tut06/tank1990menu/old-stone-brick-wall-textures.jpg");
        battleCityText = Toolkit.getDefaultToolkit().getImage("src/tut06/tank1990menu/Screenshot 2024-10-01 161850.png");
        JPanel mainPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mainBackgroundImage, 0, 0 , getWidth(), getHeight(), this);
            }
        };
        // create the black overlay background
        JPanel overlayPanel = new JPanel();
        overlayPanel.setBackground(Color.black);
        overlayPanel.setOpaque(true);
        overlayPanel.setPreferredSize(new Dimension(700, 500));
        overlayPanel.setLayout(new BorderLayout());

        //create panel for battle city text
        JPanel battleCity = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int x = (getWidth() - battleCityText.getWidth(this)) / 2;
                int y = (getHeight() - battleCityText.getHeight(this)) / 2;
                g.drawImage(battleCityText, 0, 0 , getWidth(), getHeight(), this);
            }
        };
        battleCity.setPreferredSize(new Dimension(700, 200));
        overlayPanel.add(battleCity, BorderLayout.NORTH);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;


        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 1));
        labelPanel.setOpaque(false);
        Font airstrikeFont = new Font("Airstrike", Font.PLAIN, 24);

        JLabel label1 = new JLabel("1 PLAYER");
        label1.setFont(airstrikeFont);
        label1.setForeground(Color.WHITE);
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel label2 = new JLabel("2 PLAYERS");
        label2.setFont(airstrikeFont);
        label2.setForeground(Color.WHITE);
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel label3 = new JLabel("CONSTRUCTION");
        label3.setFont(airstrikeFont);
        label3.setForeground(Color.WHITE);
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel label4 = new JLabel("ALL RIGHTS RESERVED");
        label4.setFont(airstrikeFont);
        label4.setForeground(Color.WHITE);
        label4.setHorizontalAlignment(SwingConstants.CENTER);

        // Add labels to the label panel
        labelPanel.add(label1);
        labelPanel.add(label2);
        labelPanel.add(label3);
        labelPanel.add(label4);

        overlayPanel.add(labelPanel, BorderLayout.SOUTH);
        mainPanel.add(overlayPanel, gbc);


        this.setContentPane(mainPanel);
        setPreferredSize(new Dimension(800, 600)); // Set a reasonable size
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
    public static void main(String[] args) throws Exception {
        try {
            new Tank1990Menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
