package ui.controller;

import ui.exception.InvalidEmailException;
import ui.exception.InvalidNameException;
import ui.exception.InvalidPasswordException;
import ui.model.RegisterModel;
import ui.view.RegisterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrameController {
    private RegisterModel model;
    private RegisterFrame registerFrame;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton signUpButton;
    private JButton backButton;
    private JLabel errorLabel;

    public RegisterFrameController() {
        model = new RegisterModel();
        initComponents();
        initListeners();
    }

    public void show() {
        registerFrame.setVisible(true);
    }

    public void hide() {
        registerFrame.setVisible(false);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    private void initComponents() {
        registerFrame = new RegisterFrame();
        nameField = registerFrame.getNameField();
        emailField = registerFrame.getEmailField();
        passwordField1 = registerFrame.getPasswordField1();
        passwordField2 = registerFrame.getPasswordField2();
        signUpButton = registerFrame.getSignUpButton();
        backButton = registerFrame.getBackButton();
        errorLabel = registerFrame.getErrorLabel();
    }

    private void initListeners() {
        signUpButton.addActionListener(new SignUpButtonListener());
    }

    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.setFullName(nameField.getText());
            } catch (InvalidNameException exception) {
                errorLabel.setText(exception.getMessage());
                return;
            }

            try {
                model.setEmail(emailField.getText());
            } catch (InvalidEmailException exception) {
                errorLabel.setText(exception.getMessage());
                return;
            }

            try {
                model.setPassword(passwordField1.getText(), passwordField2.getText());
            } catch(InvalidPasswordException exception) {
                errorLabel.setText(exception.getMessage());
                return;
            }

            errorLabel.setText("");
            model.createUser();

            MainMenuFrameController mainMenuFrameController = new MainMenuFrameController();
            mainMenuFrameController.show();
            hide();
        }
    }
}
