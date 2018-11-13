package ui.view;

import javax.swing.*;

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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        initRadioButtons();
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
