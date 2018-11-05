package ui.controller;

import ui.view.WelcomeFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrameController {

    private WelcomeFrame welcomeFrame;
    private JButton loginButton;
    private JButton registerButton;

    public WelcomeFrameController() {
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
        registerButton.addActionListener(e -> System.out.println("Register"));
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginFrameController loginController = new LoginFrameController();
            loginController.show();
            hide();
        }
    }
}
