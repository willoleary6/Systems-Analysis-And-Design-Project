package ui.view;

import javax.swing.*;

public class LoginFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;

    public LoginFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }
}
