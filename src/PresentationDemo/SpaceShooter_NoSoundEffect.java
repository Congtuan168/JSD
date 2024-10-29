package PresentationDemo;

import javax.imageio.ImageIO;
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

public class SpaceShooter_NoSoundEffect extends JFrame {
    private GamePanel gamePanel;

    public SpaceShooter_NoSoundEffect() {
        setTitle("Space Shooter");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        add(gamePanel);

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
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

        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
                    gamePanel.fireBullet();
                }
            }
        });

        Timer gameTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.update();
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpaceShooter_NoSoundEffect game = new SpaceShooter_NoSoundEffect();
            game.setVisible(true);
        });
    }

    class GamePanel extends JPanel {
        private List<Bullet> bullets;
        private List<Enemy> enemies;
        private List<Weapon> weapons;
        public Player player;
        private int score;
        private int lives;
        private boolean moving;
        private int enemySpeed;
        private int enemySpawnRate;
        private int bulletCount;

        public GamePanel() {
            bullets = new ArrayList<>();
            enemies = new ArrayList<>();
            weapons = new ArrayList<>();
            player = new Player(375, 500);
            score = 0;
            lives = 3;
            bulletCount = 1;
            enemySpeed = 2;
            enemySpawnRate = 5;

            setBackground(Color.BLACK);
            spawnEnemies();
        }

        public void setMoving(boolean moving) {
            this.moving = moving;
        }

        public void fireBullet() {
            for (int i = 0; i < bulletCount; i++) {
                bullets.add(new Bullet(player.x + player.width / 2 - 2 + (i * 10), player.y));
            }
        }

        private void spawnEnemies() {
            for (int i = 0; i < enemySpawnRate; i++) {
                int x = (int) (Math.random() * (getWidth() - 40));
                enemies.add(new Enemy(x, -30, enemySpeed));
            }
        }

        public void increaseDifficulty() {
            enemySpeed += 1;
            enemySpawnRate += 1;
            spawnEnemies();
        }

        public void spawnWeapons() {
            for (int i = 0; i < 3; i++) {
                int x = (int) (Math.random() * (getWidth() - 40));
                weapons.add(new Weapon(x, -30));
            }
        }

        public void update() {
            if (moving) {
                player.setX(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().x - player.width / 2); // Di chuyển theo chuột
            }

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
                        System.out.println("Game Over! Final Score: " + score);
                        System.exit(0);
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
                    bulletCount *= 2;
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
                        System.out.println("Game Over! Final Score: " + score);
                        System.exit(0);
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
                weapon.draw(g);
            }

            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 10, 20);
            g.drawString("Lives: " + lives, 700, 20);
            g.drawString("Bullets: " + bulletCount, 350, 20);
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
                image = ImageIO.read(new File("src/PresentationDemo/images/space-invaders.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setX(int x) {
            this.x = Math.max(0, Math.min(x, getWidth() - width));
        }

        public void draw(Graphics g) {
            g.drawImage(image, x, y, width, height, null);
        }

        public boolean intersects(Weapon weapon) {
            return x < weapon.x + weapon.width && x + width > weapon.x &&
                    y < weapon.y + weapon.height && y + height > weapon.y;
        }

        public boolean intersects(Enemy enemy) {
            return x < enemy.x + enemy.width && x + width > enemy.x &&
                    y < enemy.y + enemy.height && y + height > enemy.y;
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
        int speed; // Tốc độ di chuyển của kẻ thù

        public Enemy(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed; // Gán tốc độ
            loadImage();
        }

        private void loadImage() {
            try {
                image = ImageIO.read(new File("src/PresentationDemo/images/chicken.png")); // Đường dẫn mới cho hình ảnh kẻ thù
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
                image = ImageIO.read(new File("src/PresentationDemo/images/bullet.png")); // Đường dẫn cho hình ảnh vũ khí
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