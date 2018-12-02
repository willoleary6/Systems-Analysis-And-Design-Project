package ui.coordinator;


import routeCalculation.Route;
import ui.controller.FlightSearchFrameController;
import ui.controller.FlightSearchResultsController;
import ui.controller.MainMenuFrameController;
import ui.view.FlightSearchResultsFrame;

import java.util.ArrayList;

import javax.swing.*;

public class MainMenuCoordinator extends BaseCoordinator implements IMainMenuCoordinator {

    @Override
    public void start() {
        MainMenuFrameController mainMenu = new MainMenuFrameController(this);
        setViewController(mainMenu);
    }

    @Override
    public void logout() {
        int x = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you wish to log out?",
                "Logout", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (x == JOptionPane.YES_NO_OPTION) {
            ILoginCoordinator loginCoordinator = new LoginCoordinator();
            loginCoordinator.start();
            setViewController(null);
        }
    }

    @Override
    public void goToFlightSearch() {
        FlightSearchFrameController flightSearch = new FlightSearchFrameController(this);
        setViewController(flightSearch);
    }

    @Override
    public void goToFlightSearchResults(ArrayList<Route> results) {
        FlightSearchResultsController flightSearchResults = new FlightSearchResultsController(this, results);
        setViewController(flightSearchResults);
    }
}
