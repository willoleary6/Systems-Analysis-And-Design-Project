package ui.coordinator;


import ui.controller.FlightSeachFrameController;
import ui.controller.MainMenuFrameController;

public class MainMenuCoordinator extends BaseCoordinator implements IMainMenuCoordinator {

    @Override
    public void start() {
        MainMenuFrameController mainMenu = new MainMenuFrameController(this);
        setViewController(mainMenu);
    }

    @Override
    public void logout() {
        ILoginCoordinator loginCoordinator = new LoginCoordinator();
        loginCoordinator.start();
        setViewController(null);
    }

    @Override
    public void goToFlightSearch() {
        FlightSeachFrameController flightSearch = new FlightSeachFrameController(this);
        setViewController(flightSearch);
    }

    @Override
    public void goToFlightSearchResults() {

    }
}
