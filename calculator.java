import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame {
    // Holds the current input for the calculation
    private String input = "";
    // Panel to hold the calculator buttons
    private JPanel buttonsPanel;
    // Text field to display the output
    private JTextField output;

    // Constructor to set up the calculator UI
    public calculator() {
        // Set the size of the calculator window
        this.setSize(300, 450);
        // Ensure the application exits when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the title of the calculator window
        this.setTitle("Calculator");

        // Initialize the output text field
        output = new JTextField(10);
        // Set up the panel with a grid layout for the buttons
        buttonsPanel = new JPanel(new GridLayout(4, 4));

        // Define the buttons for the calculator
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Add buttons to the panel
        for (String button : buttons) {
            JButton but = new JButton(button);
            but.setFont(new Font("Monospaced", Font.BOLD, 22));
            but.addActionListener(new listener());
            but.setBackground(Color.BLUE);
            buttonsPanel.add(but);
        }

        // Configure the output text field
        output.setMaximumSize(new Dimension(185, 40));
        output.setFont(new Font("Monospaced", Font.BOLD, 25));
        output.setMargin(new Insets(0, 5, 0, 0));
        output.setText("0");

        // Add the output text field and buttons panel to the frame
        this.add(output, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
    }

    // Listener class to handle button clicks
    private class listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the button that was clicked
            JButton source = (JButton) e.getSource();
            String btnText = source.getText();

            // Handle the clear button
            if (btnText.equals("C")) {
                input = "";
                output.setText("0");
                // Handle the equals button
            } else if (btnText.equals("=")) {
                try {
                    double result = evaluateExpression(input);
                    output.setText(Double.toString(result));
                    input = Double.toString(result);
                } catch (Exception exception) {
                    output.setText("ERROR");
                    input = "";
                }
                // Handle all other buttons (numbers and operators)
            } else {
                input += btnText;
                output.setText(input);
            }
        }

        // Method to evaluate the arithmetic expression
        private double evaluateExpression(String expression) {
            try {
                String[] numbers = expression.split("[+\\-*/]");
                String[] operator = expression.split("\\d+");

                double previous = Double.parseDouble(numbers[0]);
                double current = Double.parseDouble(numbers[1]);

                if (expression.contains("+"))
                    return previous + current;
                else if (expression.contains("-"))
                    return previous - current;
                else if (expression.contains("*"))
                    return previous * current;
                else if (expression.contains("/"))
                    if (current != 0)
                        return previous / current;
                    else throw new ArithmeticException("Division by zero!");
            } catch (Exception exception) {
                throw new ArithmeticException("Invalid Input!");
            }
            return 0;
        }
    }

    // Main method to run the calculator application
    public static void main(String[] args) {
        calculator cal = new calculator();
        cal.setVisible(true);
    }
}
