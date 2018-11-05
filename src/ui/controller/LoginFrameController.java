package ui.controller;

import ui.view.LoginFrame;

import javax.swing.*;

public class LoginFrameController {
    private LoginFrame loginFrame;
    private JButton loginButton;
    private JTextField userNameField;
    private JPasswordField passwordField;

    public LoginFrameController() {
        initComponents();
        initListeners();
    }

    public void show() {
        loginFrame.setVisible(true);
    }

    public void hide() {
        loginFrame.setVisible(false);
    }

    private void initComponents() {
        loginFrame = new LoginFrame();
        loginButton = loginFrame.getLoginButton();
        userNameField = loginFrame.getUserNameField();
        passwordField = loginFrame.getPasswordField();
    }

    private void initListeners() {
        loginButton.addActionListener(e -> System.out.println("Logging in!"));
    }
}
