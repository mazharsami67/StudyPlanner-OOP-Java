import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private JTextField nameField;
    private JTextField emailField;

    public LoginWindow() {
        super("Welcome to Exam Planner");
        setLayout(new GridLayout(4, 1, 10, 10));
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        nameField = new JTextField();
        emailField = new JTextField();

        add(new JLabel("Enter Name:"));
        add(nameField);

        add(new JLabel("Enter Email:"));
        add(emailField);

        JButton startBtn = new JButton("Start Planning");
        startBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User(name, email);
                dispose(); // close login window
                new MainGUI(user); // pass user to MainGUI
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in both fields.");
            }
        });

        add(startBtn);
        setVisible(true);
    }
}

