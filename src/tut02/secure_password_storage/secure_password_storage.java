package tut02.secure_password_storage;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class secure_password_storage {
    private static final String FILENAME = "src/tut02/secure_password_storage/acc.ser";

    public static class User implements Serializable {
        private String username;
        private byte[] hashedPassword;

        public User(String username, String password) {
            this.username = username;
            this.hashedPassword = hashPassword(password);
        }

        private byte[] hashPassword(String password) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                return digest.digest(password.getBytes());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Failed to hash password", e);
            }
        }

        public boolean verifyPassword(String password) {
            byte[] inputHash = hashPassword(password);
            return MessageDigest.isEqual(inputHash, this.hashedPassword);
        }
    }

    public static void saveUser(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(user);
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    public static User loadUser() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading user: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String username = "myuser";
        String password = "mypassword";

        // Create a new user and save to file
        User user = new User(username, password);
        saveUser(user);

        // Load the user from file and verify the password
        User loadedUser = loadUser();
        if (loadedUser != null && loadedUser.verifyPassword(password)) {
            System.out.println("Password verified successfully!");
        } else {
            System.out.println("Password verification failed.");
        }
    }
}
