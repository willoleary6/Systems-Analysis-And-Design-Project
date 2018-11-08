package ui.controller;

import ui.coordinator.ILoginCoordinator;
import ui.view.WelcomeFrame;

import javax.swing.*;

public class WelcomeFrameController extends BaseFrameController {

    private ILoginCoordinator coordinator;
    private JButton loginButton;
    private JButton registerButton;

    public WelcomeFrameController(ILoginCoordinator coordinator) {
        this.coordinator = coordinator;
        initComponents();
        initListeners();
    }

    private void initComponents() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
        frame = welcomeFrame;
        loginButton = welcomeFrame.getLoginButton();
        registerButton = welcomeFrame.getRegisterButton();
    }

    private void initListeners() {
        loginButton.addActionListener(e -> coordinator.goToLogin());
        registerButton.addActionListener(e -> coordinator.goToRegister());
    }
}
