package ui.controller;

import ui.coordinator.IMainMenuCoordinator;
import ui.view.FlightSearchFrame;

import javax.swing.*;

public class FlightSeachFrameController extends BaseFrameController {
    private IMainMenuCoordinator coordinator;
    private JComboBox departureComboBox;
    private JComboBox destinationComboBox;
    private JTextField departureDateField;
    private JRadioButton costRadioButton;
    private JRadioButton timeRadioButton;
    private JButton backButton;
    private JButton searchFlightsButton;

    public FlightSeachFrameController(IMainMenuCoordinator coordinator) {
        this.coordinator = coordinator;
        initComponents();
        initListeners();
    }

    private void initComponents() {
        FlightSearchFrame flightSearchFrame = new FlightSearchFrame();
        frame = flightSearchFrame;
        departureComboBox = flightSearchFrame.getDepartureComboBox();
        destinationComboBox = flightSearchFrame.getDestinationComboBox();
        departureDateField = flightSearchFrame.getDepartureDateField();
        costRadioButton = flightSearchFrame.getCostRadioButton();
        timeRadioButton = flightSearchFrame.getTimeRadioButton();
        backButton = flightSearchFrame.getBackButton();
        searchFlightsButton = flightSearchFrame.getSearchFlightsButton();
    }

    private void initListeners() {
        backButton.addActionListener(e -> coordinator.start());
        searchFlightsButton.addActionListener(e-> coordinator.goToFlightSearchResults());
    }
}
