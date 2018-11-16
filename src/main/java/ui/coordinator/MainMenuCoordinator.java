package ui.coordinator;


import ui.controller.FlightSearchFrameController;
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
        FlightSearchFrameController flightSearch = new FlightSearchFrameController(this);
        setViewController(flightSearch);
    }

    @Override
    public void goToFlightSearchResults() {

    }
}
