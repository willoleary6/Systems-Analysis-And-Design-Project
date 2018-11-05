package ui.controller;

import ui.view.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        loginButton.addActionListener(new LoginButtonListener());
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainMenuFrameController mainMenuFrameController = new MainMenuFrameController();
            mainMenuFrameController.setUsername(userNameField.getText());
            mainMenuFrameController.setAvailablePoints(500);
            mainMenuFrameController.show();
            hide();
        }
    }
}
