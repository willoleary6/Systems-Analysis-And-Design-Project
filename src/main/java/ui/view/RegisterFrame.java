package ui.view;

import javax.swing.*;

public class RegisterFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton signUpButton;
    private JButton backButton;
    private JLabel errorLabel;

    public RegisterFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
