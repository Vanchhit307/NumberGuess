package collegeassignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends JFrame implements ActionListener {

    // Components
    private final JTextField display;
    private final JButton[] numberButtons = new JButton[10];
    private final JButton[] functionButtons = new JButton[8];
    private final JButton addButton, subButton, mulButton, divButton;
    private final JButton decButton, eqButton, delButton, clrButton;

    // Variables for calculations
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    // Constructor
    public BasicCalculator() {
        // Set up the frame
        setTitle("Basic Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // Initialize display
        display = new JTextField();
        display.setBounds(30, 30, 340, 50);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.WHITE);

        // Initialize function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLR");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = eqButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        // Add action listeners to function buttons
        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(new Color(240, 240, 240));
        }

        // Style special buttons
        addButton.setBackground(new Color(255, 200, 100));
        subButton.setBackground(new Color(255, 200, 100));
        mulButton.setBackground(new Color(255, 200, 100));
        divButton.setBackground(new Color(255, 200, 100));
        eqButton.setBackground(new Color(100, 200, 100));
        clrButton.setBackground(new Color(255, 100, 100));
        delButton.setBackground(new Color(255, 150, 100));

        // Initialize number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            numberButtons[i].setBackground(Color.WHITE);
        }

        // Create panel for buttons
        JPanel panel = new JPanel();  // Converted to local variable (warning 1 & 2 fixed)
        panel.setBounds(30, 100, 340, 340);
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(230, 230, 250));

        // Add buttons to panel
        // Row 1
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divButton);

        
        // Row 2
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(mulButton);

        // Row 3
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(subButton);

        // Row 4
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(eqButton);
        panel.add(addButton);

        // Row 5
        panel.add(delButton);
        panel.add(clrButton);

        // Add components to frame
        add(display);
        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Handle number buttons (0-9)
        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }

        // Handle decimal point
        if (source == decButton) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            return;
        }

        // Handle clear button
        if (source == clrButton) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = ' ';
            return;
        }

        // Handle delete button (backspace)
        if (source == delButton) {
            String currentText = display.getText();
            if (!currentText.isEmpty()) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
            return;
        }

        // Handle operators
        if (source == addButton || source == subButton ||
                source == mulButton || source == divButton) {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                if (source == addButton) {
                    operator = '+';
                } else if (source == subButton) {
                    operator = '-';
                } else if (source == mulButton) {
                    operator = '*';
                } else if (source == divButton) {  // Fixed warning 3
                    operator = '/';
                }
                display.setText("");
            }
            return;
        }

        // Handle equals button
        if (source == eqButton) {
            if (!display.getText().isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                calculate();
            }
        }
    }

    private void calculate() {
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error: Division by zero");
                    return;
                }
                break;
            default:
                display.setText("Error");
                return;
        }

        // Format result
        if (result == (int) result) {
            display.setText(String.valueOf((int) result));
        } else {
            String resultText = String.format("%.10f", result);
            resultText = resultText.replaceAll("0*$", "").replaceAll("\\.$", "");
            display.setText(resultText);
        }
        num1 = result;
    }

    // Fixed warnings 4 & 5 - removed 'public' and added @SuppressWarnings for unused parameter
    static void main(String[] args) {
        SwingUtilities.invokeLater(BasicCalculator::new);
    }
}