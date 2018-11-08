package ui.coordinator;


import ui.controller.MainMenuFrameController;

public class MainMenuCoordinator extends BaseCoordinator implements IMainMenuCoordinator {

    public void start() {
        MainMenuFrameController mainMenu = new MainMenuFrameController(this);
        setViewController(mainMenu);
    }

    public void logout() {
        ILoginCoordinator loginCoordinator = new LoginCoordinator();
        loginCoordinator.start();
        setViewController(null);
    }
}
