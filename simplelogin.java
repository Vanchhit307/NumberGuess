package collegeassignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simplelogin extends JFrame {

    // Create components
    JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, clearButton;

    public simplelogin() {
        // Set frame properties
        setTitle("Login Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(null); // Using absolute layout for simplicity
        panel.setBackground(new Color(240, 248, 255));

        // Username Label
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 180, 30);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(usernameField);

        // Password Label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 180, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 170, 90, 35);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(210, 170, 90, 35);
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(clearButton);

        // Info Label
        JLabel infoLabel = new JLabel("Use: admin / admin123");
        infoLabel.setBounds(140, 220, 150, 25);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.GRAY);
        panel.add(infoLabel);

        // Add panel to frame
        add(panel);

        // Button Actions
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    // Login method
    public void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Login Successful!\nWelcome " + username, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (username.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter password", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!\nTry: admin / admin123", "Error", JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Clear password
        }
    }

    // Clear method
    public void clear() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus(); // Cursor goes to username field
    }

    // Main method
    public static void main(String[] args) {
        simplelogin login = new simplelogin();
        login.setVisible(true);
    }
}