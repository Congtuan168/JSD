package PresentationDemo;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpaceShooter_SoundEffect extends JFrame {
    private GamePanel gamePanel;

    public SpaceShooter_SoundEffect() {
        setTitle("Space Shooter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        add(gamePanel);

        // Add mouse listener for player movement
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && !gamePanel.isGameOver()) {
                    gamePanel.setMoving(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gamePanel.setMoving(false);
                }
            }
        });

        // Add key listener for shooting bullets
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE && !gamePanel.isGameOver()) {
                    gamePanel.fireBullet(); // Shoot bullet when space is pressed
                }
            }
        });

        Timer gameTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gamePanel.isGameOver()) {
                    gamePanel.update();
                }
                gamePanel.repaint();
            }
        });
        gameTimer.start(); // Start the game loop
        Timer difficultyTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.increaseDifficulty();
            }
        });
        difficultyTimer.start(); // Start the difficulty increase timer

        Timer weaponTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.spawnWeapons();
            }
        });
        weaponTimer.start();
        // Play background music
        gamePanel.playBackgroundMusic();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpaceShooter_SoundEffect game = new SpaceShooter_SoundEffect();
            game.setVisible(true);
        });
    }

    // Inner class for the game panel
    class GamePanel extends JPanel {
        private List<Bullet> bullets;
        private List<Enemy> enemies;
        private List<Weapon> weapons; // List of weapons
        public Player player; // Access from outside
        private int score;
        private int lives;
        private boolean moving; // Track moving state
        private int enemySpeed; // Enemy speed
        private int enemySpawnRate; // Enemy spawn rate
        private int bulletCount; // Current bullet count
        private Clip backgroundMusic; // Clip for background music
        private Clip chickenHitClip; // Clip for chicken hit sound
        private boolean gameOver; // Track game over state

        public GamePanel() {
            bullets = new ArrayList<>();
            enemies = new ArrayList<>();
            weapons = new ArrayList<>(); // Initialize weapon list
            player = new Player(375, 500); // Starting position of the ship
            score = 0;
            lives = 3;
            bulletCount = 1; // Initialize bullet count
            enemySpeed = 2; // Initial enemy speed
            enemySpawnRate = 5; // Number of enemies spawned each time
            gameOver = false; // Game is not over initially

            setBackground(Color.BLACK);
            spawnEnemies();
            loadSounds(); // Load sounds
        }

        public boolean isGameOver() {
            return gameOver; // Return the game over state
        }

        public void setMoving(boolean moving) {
            this.moving = moving; // Update moving state
        }

        public void fireBullet() {
            for (int i = 0; i < bulletCount; i++) {
                bullets.add(new Bullet(player.x + player.width / 2 - 2 + (i * 10), player.y)); // Shoot based on current bullet count
            }
            playSound("src/PresentationDemo/sounds/tieng-dan-ban.wav"); // Play bullet sound
        }

        private void spawnEnemies() {
            for (int i = 0; i < enemySpawnRate; i++) {
                int x = (int) (Math.random() * (getWidth() - 40));
                enemies.add(new Enemy(x, -30, enemySpeed)); // Create enemies with current speed
            }
        }

        public void increaseDifficulty() {
            enemySpeed += 1; // Increase enemy speed
            enemySpawnRate += 1; // Increase number of enemies spawned
            spawnEnemies(); // Spawn new enemies
        }

        public void spawnWeapons() {
            for (int i = 0; i < 3; i++) { // Spawn 3 weapons
                int x = (int) (Math.random() * (getWidth() - 40));
                weapons.add(new Weapon(x, -30)); // Create new weapon
            }
        }

        public void update() {
            // Move player if moving
            if (moving) {
                player.setX(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x - player.width / 2); // Move with mouse
            }

            // Update bullets
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                bullet.move();
                if (bullet.y < 0) {
                    bullets.remove(i--); // Remove bullet if it's off-screen
                }
            }

            // Update enemies
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                enemy.move();

                // Check if enemy reaches the bottom
                if (enemy.y > getHeight()) {
                    enemies.remove(i--);
                    lives--; // Decrease life
                    if (lives <= 0) {
                        gameOver = true; // Set game over state
                        showGameOverScreen(); // Show game over screen
                    }
                }
            }

            // Update weapons
            for (int i = 0; i < weapons.size(); i++) {
                Weapon weapon = weapons.get(i);
                weapon.move();

                // Check if weapon reaches the bottom
                if (weapon.y > getHeight()) {
                    weapons.remove(i--);
                }

                // Check for player-weapon collisions
                if (player.intersects(weapon)) {
                    weapons.remove(i--); // Remove weapon
                    bulletCount++; // Double the bullet count
                    playSound("src/PresentationDemo/sounds/tieng-nhan-bullet.wav"); // Play pickup sound
                }
            }

            // Check for bullet-enemy collisions
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy enemy = enemies.get(j);
                    if (bullet.intersects(enemy)) {
                        bullets.remove(i--); // Remove bullet
                        enemies.remove(j--); // Remove enemy
                        score++; // Increase score
                        playChickenHitSound(); // Play hit sound
                        break; // Exit inner loop after collision
                    }
                }
            }

            // Check for player-enemy collisions
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (player.intersects(enemy)) {
                    enemies.remove(i--); // Remove enemy
                    lives--; // Decrease life when enemy hits player
                    if (lives <= 0) {
                        gameOver = true; // Set game over state
                        showGameOverScreen(); // Show game over screen
                    }
                }
            }

            // Spawn new enemies based on spawn rate
            if (Math.random() < 0.02) {
                spawnEnemies(); // Spawn enemies at the defined rate
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            player.draw(g);
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
            for (Enemy enemy : enemies) {
                enemy.draw(g);
            }
            for (Weapon weapon : weapons) {
                weapon.draw(g); // Draw weapon
            }

            // Display score, lives, and bullet count
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
            g.drawString("Lives: " + lives, 700, 20);
            g.drawString("Bullets: " + bulletCount, 350, 20); // Show bullet count

            // Show Game Over screen if the game is over
            if (gameOver) {
                showGameOver(g);
            }
        }

        private void showGameOverScreen() {
            repaint(); // Repaint to show game over screen
        }

        private void showGameOver(Graphics g) {
            g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent background
            g.fillRect(0, 0, getWidth(), getHeight()); // Fill background
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", 250, 200); // Display game over text

            // Show restart button
            JButton restartButton = new JButton("Replay");
            restartButton.setBounds(350, 300, 100, 50);
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetGame(); // Reset the game
                }
            });
            add(restartButton); // Add button to the panel
            restartButton.setFocusable(false);
            restartButton.setVisible(true);
        }

        private void resetGame() {
            bullets.clear();
            enemies.clear();
            weapons.clear();
            player = new Player(375, 500); // Reset player position
            score = 0;
            lives = 3;
            bulletCount = 1; // Reset bullet count
            enemySpeed = 2; // Reset enemy speed
            enemySpawnRate = 5; // Reset enemy spawn rate
            gameOver = false; // Reset game over state
            removeAll(); // Remove all components
            repaint(); // Repaint to refresh the panel
            spawnEnemies(); // Spawn enemies for the new game
            playBackgroundMusic(); // Play background music again
        }

        // Method to load sounds
        private void loadSounds() {
            try {
                chickenHitClip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/PresentationDemo/sounds/tieng-ga-bi-ban.wav"));
                chickenHitClip.open(audioInputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Method to play background music
        public void playBackgroundMusic() {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/PresentationDemo/sounds/nhac-nen-game.wav"));
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioInputStream);
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Loop the background music
                backgroundMusic.start(); // Start playing the music
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Method to play chicken hit sound
        private void playChickenHitSound() {
            if (chickenHitClip.isRunning()) {
                chickenHitClip.stop(); // Stop the current sound if it's still playing
            }
            chickenHitClip.setFramePosition(0); // Reset to the beginning
            chickenHitClip.start(); // Play the sound
        }

        // Method to play other sound effects
        private void playSound(String soundFile) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Inner class for the player
    class Player {
        int x, y;
        int width = 50;
        int height = 30;
        BufferedImage image;

        public Player(int x, int y) {
            this.x = x;
            this.y = y;
            loadImage();
        }

        private void loadImage() {
            try {
                image = ImageIO.read(new File("src/PresentationDemo/images/space-invaders.png")); // Path to spaceship image
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setX(int x) {
            this.x = Math.max(0, Math.min(x, getWidth() - width)); // Limit X position within bounds
        }

        public void draw(Graphics g) {
            g.drawImage(image, x, y, width, height, null); // Draw player spaceship
        }

        public boolean intersects(Weapon weapon) {
            return x < weapon.x + weapon.width && x + width > weapon.x &&
                    y < weapon.y + weapon.height && y + height > weapon.y; // Check collision with weapon
        }

        public boolean intersects(Enemy enemy) {
            return x < enemy.x + enemy.width && x + width > enemy.x &&
                    y < enemy.y + enemy.height && y + height > enemy.y; // Check collision
        }
    }

    // Inner class for bullets
    class Bullet {
        int x, y;
        int width = 5;
        int height = 10;

        public Bullet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move() {
            y -= 5; // Move bullet upwards
        }

        public void draw(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height); // Draw bullet
        }

        public boolean intersects(Enemy enemy) {
            return x < enemy.x + enemy.width && x + width > enemy.x &&
                    y < enemy.y + enemy.height && y + height > enemy.y;
        }
    }

    // Inner class for enemies
    class Enemy {
        int x, y;
        int width = 40;
        int height = 30;
        BufferedImage image;
        int speed; // Enemy movement speed

        public Enemy(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed; // Set speed
            loadImage();
        }

        private void loadImage() {
            try {
                image = ImageIO.read(new File("src/PresentationDemo/images/chicken.png")); // Path to enemy image
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void move() {
            y += speed; // Move enemy downwards based on speed
            x += (Math.random() < 0.1 ? (Math.random() < 0.5 ? -1 : 1) * 2 : 0); // Randomly move left or right
            // Keep enemy within bounds
            x = Math.max(0, Math.min(x, getWidth() - width));
        }

        public void draw(Graphics g) {
            g.drawImage(image, x, y, width, height, null); // Draw enemy
        }
    }

    // Inner class for weapons
    class Weapon {
        int x, y;
        int width = 20;
        int height = 20;
        BufferedImage image;

        public Weapon(int x, int y) {
            this.x = x;
            this.y = y;
            loadImage();
        }

        private void loadImage() {
            try {
                image = ImageIO.read(new File("src/PresentationDemo/images/bullet.png")); // Path to weapon image
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void move() {
            y += 3; // Move weapon downwards
        }

        public void draw(Graphics g) {
            g.drawImage(image, x, y, width, height, null); // Draw weapon
        }
    }
}