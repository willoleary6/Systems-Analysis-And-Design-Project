package ui.view;

import javax.swing.*;
import java.awt.*;

public class WelcomeFrame extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JLabel titleLabel;
    private JPanel mainPanel;

    public WelcomeFrame() {
        super();
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
