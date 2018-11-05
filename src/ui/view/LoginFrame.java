package ui.view;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel passwordLabel;
    private JLabel usernameLabel;

    public LoginFrame() {
        super();
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
