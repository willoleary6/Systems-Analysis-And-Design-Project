package ui.view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FlightSearchFrame extends JFrame {
    private JPanel mainPanel;
    private JComboBox departureComboBox;
    private JComboBox destinationComboBox;
    private JTextField departureDateField;
    private JRadioButton costRadioButton;
    private JRadioButton timeRadioButton;
    private JButton backButton;
    private JButton searchFlightsButton;
    private ButtonGroup buttonGroup;

    public FlightSearchFrame() {
        setTitle("Canoe - Flight Booking Service | Book Flights");
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        initRadioButtons();
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

    private void initRadioButtons() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(costRadioButton);
        buttonGroup.add(timeRadioButton);
        costRadioButton.setSelected(true);
    }

    public JComboBox getDepartureComboBox() {
        return departureComboBox;
    }

    public JComboBox getDestinationComboBox() {
        return destinationComboBox;
    }

    public JTextField getDepartureDateField() {
        return departureDateField;
    }

    public JRadioButton getCostRadioButton() {
        return costRadioButton;
    }

    public JRadioButton getTimeRadioButton() {
        return timeRadioButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getSearchFlightsButton() {
        return searchFlightsButton;
    }
}
