package ui.controller;

import ui.view.WelcomeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrameController {

    private RegisterFrameController registerFrameController;
    private LoginFrameController loginFrameController;
    private WelcomeFrame welcomeFrame;
    private JButton loginButton;
    private JButton registerButton;

    public WelcomeFrameController() {
        registerFrameController = new RegisterFrameController();
        loginFrameController = new LoginFrameController();
        initComponents();
        initListeners();
    }

    public void show() {
        welcomeFrame.setVisible(true);
    }

    public void hide() {
        welcomeFrame.setVisible(false);
    }

    private void initComponents() {
        welcomeFrame = new WelcomeFrame();
        loginButton = welcomeFrame.getLoginButton();
        registerButton = welcomeFrame.getRegisterButton();
    }

    private void initListeners() {
        loginButton.addActionListener(new LoginActionListener());
        registerButton.addActionListener(new RegisterActionListener());
        registerFrameController.addBackButtonListener(new BackButtonActionListener());
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginFrameController.show();
            hide();
        }
    }

    private class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerFrameController.show();
            hide();
        }
    }

    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            show();
            registerFrameController.hide();
            loginFrameController.hide();
        }
    }
}
