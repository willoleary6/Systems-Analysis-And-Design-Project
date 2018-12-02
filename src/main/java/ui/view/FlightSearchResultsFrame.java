package ui.view;

import javax.swing.*;

public class FlightSearchResultsFrame extends JFrame {
    private JTable flightSearchResultsTable;
    private JPanel panel1;
    private JButton bookFlightButton;
    private JButton mainMenuButton;

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
