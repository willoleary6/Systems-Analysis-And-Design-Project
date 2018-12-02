package ui.coordinator;

import routeCalculation.Route;

import java.util.ArrayList;

public interface IMainMenuCoordinator {
    void start();
    void logout();
    void goToFlightSearch();
    void goToFlightSearchResults(ArrayList<Route> results);
}
