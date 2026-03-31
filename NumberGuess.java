package collegeassignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuess extends JFrame {

    int secret, attempts;
    JTextField guessField;
    JTextArea message;

    public NumberGuess() {
        setTitle("Number Guessing Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Components
        add(new JLabel("🔢 GUESS NUMBER 1-100 🔢"));
        guessField = new JTextField(10);
        add(guessField);

        JButton guessBtn = new JButton("Guess");
        add(guessBtn);

        JButton newBtn = new JButton("New Game");
        add(newBtn);

        message = new JTextArea(12, 30);
        message.setEditable(false);
        add(new JScrollPane(message));

        // Start game
        newGame();

        // Button actions
        guessBtn.addActionListener(e -> guess());
        newBtn.addActionListener(e -> newGame());
        guessField.addActionListener(e -> guess());

        setVisible(true);
    }

    void newGame() {
        secret = new Random().nextInt(100) + 1;
        attempts = 0;
        guessField.setText("");
        guessField.setEnabled(true);
        message.setText("🎮 NEW GAME!\nGuess a number 1-100\nYou have 10 attempts\n\n");
        System.out.println("Secret: " + secret); // for testing
    }

    void guess() {
        String text = guessField.getText();

        if (text.isEmpty()) {
            message.append("❌ Please enter a number!\n\n");
            return;
        }

        int num;
        try {
            num = Integer.parseInt(text);
        } catch (Exception e) {
            message.append("❌ Enter a valid number!\n\n");
            guessField.setText("");
            return;
        }

        if (num < 1 || num > 100) {
            message.append("❌ Number must be 1-100!\n\n");
            guessField.setText("");
            return;
        }

        attempts++;

        if (num == secret) {
            message.append("✅✅✅ CORRECT! You won in " + attempts + " attempts! ✅✅✅\n");
            message.append("Click New Game to play again!\n");
            guessField.setEnabled(false);
            JOptionPane.showMessageDialog(this, "YOU WON! 🎉\nGuessed in " + attempts + " tries");
        }
        else if (num < secret) {
            message.append("❌ " + num + " - TOO LOW! (" + (10 - attempts) + " left)\n");
            if (attempts >= 10) gameOver();
        }
        else {
            message.append("❌ " + num + " - TOO HIGH! (" + (10 - attempts) + " left)\n");
            if (attempts >= 10) gameOver();
        }

        guessField.setText("");
        guessField.requestFocus();
        message.setCaretPosition(message.getDocument().getLength());
    }

    void gameOver() {
        message.append("\n💀 GAME OVER! Number was " + secret + " 💀\n");
        message.append("Click New Game to play again!\n");
        guessField.setEnabled(false);
        JOptionPane.showMessageDialog(this, "GAME OVER!\nNumber was " + secret);
    }

    public static void main(String[] args) {
        new NumberGuess();
    }
}