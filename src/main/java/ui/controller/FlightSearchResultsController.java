package ui.controller;

import routeCalculation.Route;
import ui.coordinator.IMainMenuCoordinator;
import ui.model.FlightSearchResultsTableModel;
import ui.view.FlightSearchResultsFrame;

import javax.swing.*;
import java.util.ArrayList;

public class FlightSearchResultsController extends BaseFrameController {
    private JTable flightSearchResultsTable;
    private JButton bookFlightButton;
    private JButton searchFlightsButton;
    private IMainMenuCoordinator coordinator;

    public FlightSearchResultsController(IMainMenuCoordinator coordinator, ArrayList<Route> routes) {
        this.coordinator = coordinator;
        initComponents(routes);
        initListeners();
    }

    private void initComponents(ArrayList<Route> routes) {
        FlightSearchResultsFrame flightSearchResultsFrame = new FlightSearchResultsFrame();
        this.frame = flightSearchResultsFrame;
        bookFlightButton = flightSearchResultsFrame.getBookFlightButton();
        searchFlightsButton = flightSearchResultsFrame.getSearchFlightsButton();
        flightSearchResultsTable = flightSearchResultsFrame.getFlightSearchResultsTable();
        FlightSearchResultsTableModel tableModel = new FlightSearchResultsTableModel(routes);
        flightSearchResultsTable.setModel(tableModel);
    }

    private void initListeners() {
        searchFlightsButton.addActionListener(e -> coordinator.goToFlightSearch());
        bookFlightButton.addActionListener(e -> System.out.print("Book flight " + flightSearchResultsTable.getSelectedRow()));
    }
}
