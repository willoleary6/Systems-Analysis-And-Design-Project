package ui.view;

import javax.swing.*;

public class FlightSearchResultsFrame extends JFrame {
    private JTable flightSearchResultsTable;
    private JPanel panel1;
    private JButton bookFlightButton;
    private JButton mainMenuButton;


    public FlightSearchResultsFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setContentPane(panel1);
        setLocationRelativeTo(null);
    }

    public JTable getFlightSearchResultsTable() {
        return flightSearchResultsTable;
    }

    public JButton getBookFlightButton() {
        return bookFlightButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
