package ui.view;

import javax.swing.*;

public class MainMenuFrame extends JFrame {
    private JPanel mainPanel;
    private JButton bookFlightsButton;
    private JButton cancelFlightsButton;
    private JButton orderHistoryButton;
    private JButton logoutButton;
    private JLabel usernameLabel;
    private JLabel availablePointsLabel;

    public MainMenuFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JButton getBookFlightsButton() {
        return bookFlightsButton;
    }

    public JButton getCancelFlightsButton() {
        return cancelFlightsButton;
    }

    public JButton getOrderHistoryButton() {
        return orderHistoryButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JLabel getAvailablePointsLabel() {
        return availablePointsLabel;
    }
}
