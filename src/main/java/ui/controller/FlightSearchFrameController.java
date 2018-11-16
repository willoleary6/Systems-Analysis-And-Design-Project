package ui.controller;

import routeCalculation.Airport;
import ui.coordinator.IMainMenuCoordinator;
import ui.model.AirportComboBoxModel;
import ui.model.FlightSearchModel;
import ui.view.FlightSearchFrame;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FlightSearchFrameController extends BaseFrameController implements PropertyChangeListener {
    private IMainMenuCoordinator coordinator;
    private JComboBox departureComboBox;
    private JComboBox destinationComboBox;
    private JTextField departureDateField;
    private JRadioButton costRadioButton;
    private JRadioButton timeRadioButton;
    private JButton backButton;
    private JButton searchFlightsButton;
    private FlightSearchModel model;

    public FlightSearchFrameController(IMainMenuCoordinator coordinator) {
        this.coordinator = coordinator;
        initComponents();
        initListeners();
        this.model = new FlightSearchModel(this);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Airport[] airportList = (Airport[]) evt.getNewValue();

        ComboBoxModel boxModel1 = new AirportComboBoxModel(airportList);
        ComboBoxModel boxModel2 = new AirportComboBoxModel(airportList);
        departureComboBox.setModel(boxModel1);
        destinationComboBox.setModel(boxModel2);
    }
}
