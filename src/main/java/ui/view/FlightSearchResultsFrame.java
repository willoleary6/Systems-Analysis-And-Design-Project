package ui.view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FlightSearchResultsFrame extends JFrame {
    private JTable flightSearchResultsTable;
    private JPanel panel1;
    private JButton bookFlightButton;
    private JButton searchFlightsButton;


    public FlightSearchResultsFrame() {
        setTitle("Canoe - Flight Booking Service | Search Results");
        setSize(500, 500);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int x = JOptionPane.showConfirmDialog(
                        null,
                        "You are currently in the middle of a flight search.\nAre you sure you wish to exit Canoe? Any changes will be lost.",
                        "Exit Canoe",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (x == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }

    public JTable getFlightSearchResultsTable() {
        return flightSearchResultsTable;
    }

    public JButton getBookFlightButton() {
        return bookFlightButton;
    }

    public JButton getSearchFlightsButton() {
        return searchFlightsButton;
    }
}
