package ui.controller;

import ui.view.MainMenuFrame;

import javax.swing.*;

public class MainMenuFrameController {
    private MainMenuFrame mainMenuFrame;
    private JButton bookFlightsButton;
    private JButton cancelFlightsButton;
    private JButton orderHistoryButton;
    private JButton logoutButton;
    private JLabel usernameLabel;
    private JLabel availablePointsLabel;

    public MainMenuFrameController() {
        initComponents();
        initListeners();
    }

    public void show() {
        mainMenuFrame.setVisible(true);
    }

    public void hide() {
        mainMenuFrame.setVisible(false);
    }

    public void setUsername(String username) {
        usernameLabel.setText(username);
    }

    public void setAvailablePoints(int points) {
        availablePointsLabel.setText("Available points: " + points);
    }

    private void initComponents() {
        mainMenuFrame = new MainMenuFrame();
        bookFlightsButton = mainMenuFrame.getBookFlightsButton();
        cancelFlightsButton = mainMenuFrame.getCancelFlightsButton();
        orderHistoryButton = mainMenuFrame.getOrderHistoryButton();
        logoutButton = mainMenuFrame.getLogoutButton();
        usernameLabel = mainMenuFrame.getUsernameLabel();
        availablePointsLabel = mainMenuFrame.getAvailablePointsLabel();
    }

    private void initListeners() {

    }
}
