package UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainMenu extends JFrame {

    private JPanel panel = new JPanel();
    private Button loginButton = new Button("Login");
    private Button registerButton = new Button("Register");

    public MainMenu() {
        super("Canoe Main Menu");
        panel.setLayout(new GridLayout(2,1));
        panel.add(loginButton);
        panel.add(registerButton);
        Border padding = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        panel.setBorder(padding);

        add(panel);

        setSize(300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());
    }

    private void login() {
        System.out.println("Logging in...");
    }

    private void register() {
        System.out.println("Registering...");
    }

}
