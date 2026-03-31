package collegeassignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Digitalclock extends JFrame {

    // Create JLabel to display time
    private JLabel timeLabel;

    // Constructor
    public Digitalclock() {
        // Set up the frame
        setTitle("Digital Clock");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Create time label
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setOpaque(true);

        // Add label to frame
        add(timeLabel);

        // Create Timer (updates every 1000 milliseconds = 1 second)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });

        // Start the timer
        timer.start();

        // Initial time display
        updateTime();
    }

    // Method to update time
    private void updateTime() {
        // Get current date and time
        Date now = new Date();

        // Format: HH:mm:ss (24-hour format)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = timeFormat.format(now);

        // Set the time to label
        timeLabel.setText(currentTime);
    }

    // Main method
    public static void main(String[] args) {
        // Run on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Digitalclock().setVisible(true);
            }
        });
    }
}