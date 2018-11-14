package ui.controller;

import ui.coordinator.ILoginCoordinator;
import ui.model.RegisterModel;
import ui.view.RegisterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;

public class RegisterFrameController extends BaseFrameController {
    private ILoginCoordinator coordinator;
    private RegisterModel model;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton signUpButton;
    private JButton backButton;
    private JLabel errorLabel;

    public RegisterFrameController(ILoginCoordinator coordinator) {
        this.coordinator = coordinator;
        model = new RegisterModel();
        initComponents();
        initListeners();
    }

    private void initComponents() {
        RegisterFrame registerFrame = new RegisterFrame();
        frame = registerFrame;
        usernameField = registerFrame.getUsernameField();
        emailField = registerFrame.getEmailField();
        passwordField1 = registerFrame.getPasswordField1();
        passwordField2 = registerFrame.getPasswordField2();
        signUpButton = registerFrame.getSignUpButton();
        backButton = registerFrame.getBackButton();
        errorLabel = registerFrame.getErrorLabel();
    }

    private void initListeners() {
        backButton.addActionListener(e -> coordinator.start());
        signUpButton.addActionListener(new SignUpButtonListener());
    }

    private class SignUpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                model.setUserName(usernameField.getText());
                model.setEmail(emailField.getText());
                model.setPassword(passwordField1.getText(), passwordField2.getText());
                errorLabel.setText(" ");

                model.createUser();
                coordinator.goToMainMenu();

            } catch (InvalidParameterException exception) {
                errorLabel.setText(exception.getMessage());
            }
        }
    }
}
