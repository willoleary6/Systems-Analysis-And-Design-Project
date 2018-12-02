package ui.controller;
import ui.coordinator.ILoginCoordinator;
import ui.model.LoginModel;
import ui.view.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;

public class LoginFrameController extends BaseFrameController {
    private ILoginCoordinator coordinator;
    private LoginModel model;
    private JButton loginButton, backButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel errorLabel;

    public LoginFrameController(ILoginCoordinator coordinator) {
        this.coordinator = coordinator;
        model = new LoginModel();
        initComponents();
        initListeners();
    }

    private void initComponents() {
        LoginFrame loginFrame = new LoginFrame();
        frame = loginFrame;
        loginButton = loginFrame.getLoginButton();
        backButton = loginFrame.getBackButton();
        usernameField = loginFrame.getUsernameField();
        passwordField = loginFrame.getPasswordField();
        errorLabel = loginFrame.getErrorLabel();
    }

    private void initListeners() {
        loginButton.addActionListener(new LoginButtonListener());
        backButton.addActionListener(e -> coordinator.start());
    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.setUsername(usernameField.getText());
                model.setPassword(passwordField.getText());
                if(model.login())
                    coordinator.goToMainMenu();
                else
                    errorLabel.setText("Invalid username or password");
            } catch (InvalidParameterException exception) {
                errorLabel.setText(exception.getMessage());
            }
        }
    }
}
