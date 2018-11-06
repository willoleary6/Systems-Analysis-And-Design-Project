package ui.controller;

import ui.exception.InvalidEmailException;
import ui.exception.InvalidPasswordException;
import ui.model.LoginModel;
import ui.view.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrameController {
    private LoginModel model;
    private LoginFrame loginFrame;
    private JButton loginButton;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel errorLabel;

    public LoginFrameController() {
        model = new LoginModel();
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
        emailField = loginFrame.getEmailField();
        passwordField = loginFrame.getPasswordField();
        errorLabel = loginFrame.getErrorLabel();
    }

    private void initListeners() {
        loginButton.addActionListener(new LoginButtonListener());
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.setEmail(emailField.getText());
            } catch (InvalidEmailException exception) {
                errorLabel.setText(exception.getMessage());
                return;
            }

            try {
                model.setPassword(passwordField.getText());
            } catch (InvalidPasswordException exception) {
                errorLabel.setText(exception.getMessage());
                return;
            }

            model.getUser();

            MainMenuFrameController mainMenuFrameController = new MainMenuFrameController();
            mainMenuFrameController.show();
            hide();
        }
    }
}
