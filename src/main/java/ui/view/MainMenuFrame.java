package ui.view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenuFrame extends JFrame {
    private JPanel mainPanel;
    private JButton bookFlightsButton;
    private JButton cancelFlightsButton;
    private JButton orderHistoryButton;
    private JButton logoutButton;
    private JLabel usernameLabel;
    private JLabel availablePointsLabel;

    public MainMenuFrame() {
        setTitle("Canoe - Flight Booking Service");
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int x = JOptionPane.showConfirmDialog(
                        null,
                        "You are currently logged in. Are you sure you wish to exit Canoe?",
                        "Exit Canoe",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (x == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

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
